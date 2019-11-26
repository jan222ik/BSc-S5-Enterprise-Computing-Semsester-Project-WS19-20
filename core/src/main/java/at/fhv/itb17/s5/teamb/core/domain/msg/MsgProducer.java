package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MsgProducer {

    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    private HashMap<String, Destination> destinations;
    private HashMap<Destination, MessageProducer> msgProducers;

    public MsgProducer() {
    }

    public MsgProducer(MsgRepository repo) {
        topics = repo.getAllTopics();
    }

    public void init(String brokerUrl) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        for (MsgTopic msgTopic : topics) {
            destinations.put(msgTopic.getName(), session.createTopic("VirtualTopic." + msgTopic.getName()));
        }

        // Create a MessageProducer from the Session to the Topic or Queue
        msgProducers = new HashMap<>();
        destinations.forEach((msgTopic, destination1) -> {
            try {
                MessageProducer producer = session.createProducer(destination1);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                msgProducers.put(destination1, producer);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendCreatedMessages() throws JMSException {

        // Create a messages
        List<TextMessage> createdMessages = new LinkedList<>();
        for (MsgTopic topic : topics) {
            for (int i = 0; i < 10; i++) {
                createdMessages.add(createMessage("Message " + i, "Message Nr. " + i + " for "
                        + topic.getName(), topic));
            }
        }

        //Send messages
        for (TextMessage createdMessage : createdMessages) {
            sendMessage(createdMessage);
        }
    }

    public void close() throws JMSException {
        session.close();
        connection.close();
    }

    private TextMessage createMessage(String header, String content, MsgTopic topic) throws JMSException {
        TextMessage message = session.createTextMessage(content);
        message.setStringProperty("header", header);
        message.setStringProperty("topic", topic.getName());
        return message;
    }

    public boolean createMessagePub(String header, String content, MsgTopic topic) {
        TextMessage message = null;
        try {
            message = this.createMessage(header, content, topic);
            sendMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return message != null;
    }

    private void sendMessage(TextMessage message) throws JMSException {
        MessageProducer producer = msgProducers.get(destinations.get(message.getStringProperty("topic")));
        if (producer != null) {
            producer.send(message);
        }
    }
}

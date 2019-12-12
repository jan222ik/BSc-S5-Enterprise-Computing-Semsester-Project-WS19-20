package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MsgProducer {

    private static final Logger logger = LogManager.getLogger(MsgProducer.class);

    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    private HashMap<String, Destination> destinations = new HashMap<>();
    private HashMap<Destination, MessageProducer> msgProducers = new HashMap<>();

    public MsgProducer() {
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        MsgTopic theater = new MsgTopic("THEATER", false);
        this.topics = new LinkedList<>();
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
        topics.add(theater);
    }

    public MsgProducer(MsgRepository repo) {
        topics = repo.getAllTopics();
    }

    public void init(String brokerUrl, String clientId) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        connection.start();

        // Create a Session
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        for (MsgTopic msgTopic : topics) { //TODO filter topic names to prevent doublettes -> doesn't hahsmap put just overwrite?
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
                logger.catching(e);
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
            logger.catching(e);
            return false;
        }
        //noinspection ConstantConditions
        return message != null;
    }

    private void sendMessage(TextMessage message) throws JMSException {
        MessageProducer producer = msgProducers.get(destinations.get(message.getStringProperty("topic")));
        if (producer != null) {
            producer.send(message);
        }
    }
}

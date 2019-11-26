package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class MsgProducer implements Runnable {

    private static final String TOPIC = "TEST.FOO";
    private List<MsgTopic> topics;
    private Session session;

    public MsgProducer() {
        topics = new LinkedList<>();
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
    }

    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            List<Destination> destinations = new LinkedList<>();
            for (MsgTopic msgTopic : topics) {
                destinations.add(session.createTopic(msgTopic.getName()));
            }
            //Destination destination = session.createTopic(TOPIC);

            // Create a MessageProducer from the Session to the Topic or Queue
            List<MessageProducer> msgProducers = new LinkedList<>();
            for (Destination destination1 : destinations) {
                MessageProducer producer = session.createProducer(destination1);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                msgProducers.add(producer);
            }
            //MessageProducer producer = session.createProducer(destination);


            // Create a messages
            List<TextMessage> createdMessages = new LinkedList<>();
            for (MsgTopic topic : topics) {
                for (int i = 0; i < 10; i++) {
                    createdMessages.add(createMessage("Message " + i, "Message Nr. " + i + " for" + topic.getName(), topic));
                }
            }

            //Send messages
            for (TextMessage createdMessage : createdMessages) {
                for (MessageProducer msgProducer : msgProducers) {
                    sendMessage(createdMessage, msgProducer);
                }
            }

            // Clean up
            session.close();
            connection.close();
        } catch (
                Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
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
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return message != null;
    }

    private void sendMessage(TextMessage message, MessageProducer producer) throws JMSException {
        if (("topic:" + message.getStringProperty("topic")).equals(producer.getDestination().toString().replace("/", ""))) {
            producer.send(message);
        }
    }
}

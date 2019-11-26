package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class MsgConsumer implements ExceptionListener {
    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    private List<MessageConsumer> msgConsumers;

    public MsgConsumer() {
        topics = new LinkedList<>();
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
    }

    public MsgConsumer(List<MsgTopic> topics) {
        this.topics = topics;
    }

    public void init(String brokerUrl) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(this);

        // Create a Session
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        List<Destination> destinations = new LinkedList<>();
        for (MsgTopic msgTopic : topics) {
            destinations.add(session.createTopic("VirtualTopic." + msgTopic.getName()));
        }

        // Create a MessageConsumer from the Session to the Topic or Queue
        msgConsumers = new LinkedList<>();
        for (Destination destination1 : destinations) {
            MessageConsumer consumer = session.createConsumer(destination1);
            msgConsumers.add(consumer);
        }
    }

    public void waitForMsgs(int time) {
        for (MessageConsumer msgConsumer : msgConsumers) {
            try {
                waitForMessage(msgConsumer, time);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws JMSException {
        session.close();
        connection.close();
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    private void waitForMessage(MessageConsumer consumer, int milliseconds) throws JMSException {
        Message message = consumer.receive(milliseconds);
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            textMessage.acknowledge();
            System.out.println(this.hashCode() + "Received Topic: " + textMessage.getStringProperty("topic"));
            System.out.println("Received header: " + textMessage.getStringProperty("header"));
            System.out.println("Received text: " + text);
        } else {
            System.out.println("Received message: " + message);
        }
    }
}

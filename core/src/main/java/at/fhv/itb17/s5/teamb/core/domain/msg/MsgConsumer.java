package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class MsgConsumer implements Runnable, ExceptionListener {
    private List<MsgTopic> topics;
    private Session session;

    public MsgConsumer() {
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

            connection.setExceptionListener(this);

            // Create a Session
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            //Destination destination = session.createTopic("TEST.FOO");
            List<Destination> destinations = new LinkedList<>();
            for (MsgTopic msgTopic : topics) {
                destinations.add(session.createTopic(msgTopic.getName()));
            }

            // Create a MessageConsumer from the Session to the Topic or Queue
            //MessageConsumer consumer = session.createConsumer(destination);
            List<MessageConsumer> msgConsumers = new LinkedList<>();
            for (Destination destination1 : destinations) {
                MessageConsumer consumer = session.createConsumer(destination1);
                msgConsumers.add(consumer);
            }

            // Wait for a message
           /* Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                textMessage.acknowledge();
                System.out.println("Received Topic: " + textMessage.getStringProperty("topic"));
                System.out.println("Received header: " + textMessage.getStringProperty("header"));
                System.out.println("Received text: " + text);
                System.out.println("Ack: ");
            } else {
                System.out.println("Received message: " + message);
            }



            consumer.close(); */
            for (MessageConsumer msgConsumer : msgConsumers) {
                waitForMessage(msgConsumer);
            }
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    private void waitForMessage(MessageConsumer consumer) throws JMSException {
        Message message = consumer.receive(1000);
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            textMessage.acknowledge();
            System.out.println("Received Topic: " + textMessage.getStringProperty("topic"));
            System.out.println("Received header: " + textMessage.getStringProperty("header"));
            System.out.println("Received text: " + text);
            System.out.println("Ack: ");
        } else {
            System.out.println("Received message: " + message);
        }
        consumer.close();
    }
}

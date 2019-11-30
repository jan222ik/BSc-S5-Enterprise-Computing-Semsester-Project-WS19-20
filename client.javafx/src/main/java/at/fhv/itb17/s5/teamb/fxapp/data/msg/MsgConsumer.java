package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class MsgConsumer implements ExceptionListener, MessageListener {
    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    private List<MessageConsumer> msgConsumers;
    private List<TextMessage> messages;

    public MsgConsumer() {
        this.topics = new LinkedList<>();
        this.messages = new LinkedList<>();
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
    }

    public MsgConsumer(List<MsgTopic> topics) {
        this.topics = topics;
        this.messages = new LinkedList<>();
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
            Queue queue = session.createQueue("Consumer." + this.hashCode() + ".VirtualTopic." + msgTopic.getName());
            destinations.add(queue);
        }

        // Create a MessageConsumer from the Session to the Topic or Queue
        msgConsumers = new LinkedList<>();
        for (Destination destination1 : destinations) {
            MessageConsumer consumer = session.createConsumer(destination1);
            consumer.setMessageListener(this);
            msgConsumers.add(consumer);
        }
    }

    public void close() throws JMSException {
        session.close();
        connection.close();
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("textMessage = " + textMessage);
            addMessage(textMessage);
            String text;
            try {
                text = textMessage.getText();
                System.out.println(this.hashCode() + "Received Topic: " + textMessage.getStringProperty("topic"));
                System.out.println("Received header: " + textMessage.getStringProperty("header"));
                System.out.println("Received text: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Received message: " + message);
        }
    }

    private void addMessage(TextMessage textMessage) {
        System.out.println("messagesadd = " + messages.size());
        messages.add(textMessage);
    }

    public List<TextMessage> getMessages() {
        System.out.println("messagesget = " + messages.size());
        return messages;
    }
}


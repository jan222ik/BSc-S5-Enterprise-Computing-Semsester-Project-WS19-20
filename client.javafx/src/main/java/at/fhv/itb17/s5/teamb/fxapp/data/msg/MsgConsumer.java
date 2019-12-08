package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class MsgConsumer implements ExceptionListener, MessageListener {

    private static final Logger logger = LogManager.getLogger(MsgConsumer.class);

    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    @SuppressWarnings({"FieldCanBeLocal", "squid:1450", "MismatchedQueryAndUpdateOfCollection"})
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
        logger.debug("JMS Exception occured.  Shutting down client.");
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            logger.debug("textMessage = {}", textMessage);
            addMessage(textMessage);
            String text;
            try {
                text = textMessage.getText();
                logger.debug("{} Received Topic: {}", this.hashCode(), textMessage.getStringProperty("topic"));
                logger.debug("Received header: {}", textMessage.getStringProperty("header"));
                logger.debug("Received text: {}", text);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        } else {
            logger.debug("Received message: {}", message);
        }
    }

    private void addMessage(TextMessage textMessage) {
        logger.debug("messagesadd = {}", messages.size());
        messages.add(textMessage);
    }

    public List<TextMessage> getMessages() {
        logger.debug("messagesget = {}", messages.size());
        return messages;
    }
}


package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.PublishSubject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MsgAsyncServiceImpl implements ExceptionListener, MessageListener, MsgAsyncService {

    private static final Logger logger = LogManager.getLogger(MsgAsyncServiceImpl.class);
    private Disposable dispose;

    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    @SuppressWarnings({"FieldCanBeLocal", "squid:1450", "MismatchedQueryAndUpdateOfCollection"})
    private List<MessageConsumer> msgConsumers;
    private List<TextMessage> messages;
    private ObservableList<MsgWrapper> outList = new ObservableList<>();

    public MsgAsyncServiceImpl() {
        this.topics = new LinkedList<>();
        this.messages = new LinkedList<>();
    }

    @Override
    public void init(String brokerUrl) throws JMSException {
        logger.debug("Init MsgAsyncServiceImpl");
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
        //msgConsumers = new LinkedList<>();
        for (Destination destination1 : destinations) {
            MessageConsumer consumer = session.createConsumer(destination1);
            consumer.setMessageListener(this);
            //msgConsumers.add(consumer);
        }
    }

    @Override
    public List<MsgWrapper> getAllMsgs() {
        return outList.list;
    }

    @Override
    public void setPresenter(ApplicationMain presenter) {
        dispose = outList.getObservable().subscribeOn(JavaFxScheduler.platform()).subscribe(presenter::showNewMsg);
    }

    @Override
    public synchronized void onException(JMSException exception) {
        exception.printStackTrace();
    }

    @Override
    public synchronized void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            logger.debug("textMessage = {}", textMessage);
            String text;
            try {
                text = textMessage.getText();
                String topic = textMessage.getStringProperty("topic");
                String header = textMessage.getStringProperty("header");
                logger.debug("{} Received Topic: {}", this.hashCode(), topic);
                logger.debug("Received header: {}", header);
                logger.debug("Received text: {}", text);
                //Maybe textMessage.getJMSTimestamp() instead of time stamp //TODO
                outList.add(new MsgWrapper(topic, text, textMessage, LocalDateTime.now(), false, header)); //ack = false TODO CHECK IF THIS COULD BE AN ERROR
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            logger.debug("Received message: {}", message);
        }
    }

    @Override
    public void close() throws JMSException {
        if (session != null) {
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (dispose != null && !dispose.isDisposed()) {
            dispose.dispose();
        }
    }

    public static class ObservableList<T> {

        protected final List<T> list;
        protected final PublishSubject<T> onAdd;

        public ObservableList() {
            this.list = new ArrayList<>();
            this.onAdd = PublishSubject.create();
        }
        public void add(T value) {
            list.add(value);
            onAdd.onNext(value);
        }
        public Observable<T> getObservable() {
            return onAdd;
        }

    }
}

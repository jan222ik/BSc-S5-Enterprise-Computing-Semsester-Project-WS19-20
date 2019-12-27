package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.MsgTopicMapper;
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

import javax.jms.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MsgAsyncServiceImpl implements ExceptionListener, MessageListener, MsgAsyncService {

    private static final Logger logger = LogManager.getLogger(MsgAsyncServiceImpl.class);
    private Disposable dispose;
    private List<MsgTopic> topics;
    private Session session;
    private Connection connection;
    private HashMap<String, Destination> destinations = new HashMap<>();
    private HashMap<Destination, TopicSubscriber> msgConsumers = new HashMap<>();
    private HashMap<Topic, String> subNames = new HashMap<>();
    private List<TextMessage> messages;
    private ObservableList<MsgWrapper> outList = new ObservableList<>();

    public MsgAsyncServiceImpl(List<MsgTopic> topics) {
        this.topics = topics;
        this.messages = new LinkedList<>();
    }

    public MsgAsyncServiceImpl() {
        this.topics = new LinkedList<>();
        this.messages = new LinkedList<>();
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
    }

    @Override
    public void init(String brokerUrl, String clientId) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        connection.setExceptionListener(this);

        // Create a Session
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        for (MsgTopic msgTopic : topics) {
            Topic topic = session.createTopic("VirtualTopic." + msgTopic.getName());
            //Topic topic = session.createTopic("Consumer." + this.hashCode() + ".VirtualTopic." + msgTopic.getName());
            destinations.put(msgTopic.getName(), topic);
            subNames.put(topic, msgTopic.getName());
        }

        // Create a MessageConsumer from the Session to the Topic or Queue
        destinations.forEach((topic, destination) -> {
            TopicSubscriber consumer = null;
            try {
                consumer = session.createDurableSubscriber((Topic) destination, subNames.get(destination));
                consumer.setMessageListener(this);
                msgConsumers.put(destination, consumer);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        connection.start();
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
            topics.forEach((msgTopic -> {
                try {
                    session.unsubscribe(msgTopic.getName());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }));
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (dispose != null && !dispose.isDisposed()) {
            dispose.dispose();
        }
    }

    @Override
    public void setTopics(List<MsgTopic> topics) {

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

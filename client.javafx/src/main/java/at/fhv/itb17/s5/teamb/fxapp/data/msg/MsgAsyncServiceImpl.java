package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.PublishSubject;
import javafx.application.Platform;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.TestOnly;

import javax.jms.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgAsyncServiceImpl implements ExceptionListener, MessageListener, MsgAsyncService {

    private static final Logger logger = LogManager.getLogger(MsgAsyncServiceImpl.class);

    private Disposable dispose;
    private List<MsgTopicDTO> topics;
    private Session session;
    private Connection connection;
    private HashMap<String, Destination> destinations = new HashMap<>();
    private HashMap<Topic, String> subNames = new HashMap<>();
    private ObservableList<MsgWrapper> outList = new ObservableList<>();

    public MsgAsyncServiceImpl() {
        //empty Constructor
    }

    @Override
    public void init(String brokerUrl, String clientId) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        connection.setExceptionListener(this);
        connection.start();

        // Create a Session
        session = connection.createSession(false, ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        for (MsgTopicDTO msgTopic : topics) {
            logger.info("Consumer Topic is {}", msgTopic.getName());
            Topic topic = session.createTopic("VirtualTopic." + msgTopic.getName());
            destinations.put(msgTopic.getName(), topic);
            subNames.put(topic, msgTopic.getName());
        }

        // Create a MessageConsumer from the Session to the Topic or Queue
        destinations.forEach((topic, destination) -> {
            TopicSubscriber consumer;
            try {
                consumer = session.createDurableSubscriber((Topic) destination, subNames.get(destination));
                consumer.setMessageListener(this);
            } catch (JMSException e) {
                logger.catching(e);
            }
        });
    }

    @Override
    public List<MsgWrapper> getAllMsgs() {
        return outList.list;
    }

    @Override
    public void setPresenter(ApplicationMain presenter) {
        dispose = outList.getObservable().subscribeOn(JavaFxScheduler.platform()).subscribe((presenter::showNewMsg), Throwable::printStackTrace);
    }

    @Override
    public synchronized void onException(JMSException exception) {
        logger.catching(exception);
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
                MsgWrapper msgWrapper = new MsgWrapper(topic, text, textMessage, LocalDateTime.now(), false, header);
                Platform.runLater(() -> outList.add(msgWrapper));
            } catch (JMSException e) {
                logger.catching(e);
            }
        } else {
            logger.debug("Received message: {}", message);
        }
    }

    @Override
    public boolean close() throws JMSException {
        if (session != null) {
            if (topics != null) {
                topics.forEach((msgTopic -> {
                    try {
                        session.unsubscribe(msgTopic.getName());
                    } catch (JMSException e) {
                        logger.catching(e);
                    }
                }));
            }
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (dispose != null && !dispose.isDisposed()) {
            dispose.dispose();
        }
        return true;
    }

    @Override
    public void setTopics(List<MsgTopicDTO> topics) {
        this.topics = topics;
    }

    @Override
    @TestOnly
    public void acknowledgeTest() {
        List<MsgWrapper> allMsgs = getAllMsgs();
        for (MsgWrapper allMsg : allMsgs) {
            if (allMsg.getTopicName().equals("TEST")) {
                try {
                    allMsg.getTextMessage().acknowledge();
                } catch (JMSException e) {
                    logger.warn("JMSException acknowledgeTest", e);
                }
            }
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

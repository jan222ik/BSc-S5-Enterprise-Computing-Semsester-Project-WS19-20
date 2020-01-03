package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBBookingServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBController;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBSearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBTopicServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.msg.MsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import com.airhacks.afterburner.injection.Injector;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

import javax.jms.JMSException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class BeanManager implements SetupManager {
    private static final int totalSteps = 9;

    public static final String TCP_LOCAL = "tcp://localhost:61616";
    public static final String TCP_JENKINS = "tcp://10.0.51.91:61616";

    private EJBController controller;
    private boolean isConnected = true;
    private SearchService searchService;
    private BookingService bookingService;
    private MsgTopicService msgTopicService;
    private SetupCallback callbackConsumer;
    private MsgAsyncService msgAsyncService;

    private boolean remote = false;

    private List<Disposable> disposables;
    private ApplicationMain presenter;

    @Override
    public boolean create() {
        try {
            disposables = new LinkedList<>();
            controller = new EJBController();
            searchService = new EJBSearchServiceImpl(controller);
            bookingService = new EJBBookingServiceImpl(controller);
            msgTopicService = new EJBTopicServiceImpl(controller);
            msgAsyncService = new MsgAsyncServiceImpl();
            msgAsyncService.setPresenter(presenter);
            Injector.setModelOrService(SearchService.class, searchService);
            Injector.setModelOrService(BookingService.class, bookingService);
            Injector.setModelOrService(MsgTopicService.class, msgTopicService);
            Injector.setModelOrService(EJBController.class, controller);
            Injector.setModelOrService(MsgAsyncService.class, msgAsyncService);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RMIConnectionStatus connect(String host, int port) {
        if (controller != null) {
            notifyCallbackConsumer("Connecting to Server (EJB)", 0, totalSteps);
            boolean connected = controller.connect(host);
            if (connected) {
                notifyCallbackConsumer("Connected to Server (EJB)", 1, totalSteps);
            }
            if (host.equals("10.0.51.91")) {
                remote = true;
            }
            return connected ? RMIConnectionStatus.CONNECTED : RMIConnectionStatus.NO_CONNECTION;
        } else {
            return RMIConnectionStatus.NO_CONNECTION;
        }
    }

    @Override
    public RMIConnectionStatus authenticate(String user, String pwd) {
        RMIConnectionStatus status;
        notifyCallbackConsumer("Checking Connection Status (EJB)", 2, totalSteps);
        if (isConnected) {
            notifyCallbackConsumer("Initializing Search Component (EJB)", 3, totalSteps);
            status = searchService.init();
            if (status == RMIConnectionStatus.CONNECTED) {
                notifyCallbackConsumer("Successfully initialized Search Component (EJB)", 4, totalSteps);
                notifyCallbackConsumer("Initializing Messaging Component (EJB)", 5, totalSteps);
                status = msgTopicService.doLoginMsgTopic(user, pwd);
                if (status == RMIConnectionStatus.CONNECTED) {
                    setMsgTopics();
                    initMsgAsync(user, remote);
                    notifyCallbackConsumer("Successfully initialized Messaging Component (EJB)", 6, totalSteps);
                    notifyCallbackConsumer("Initializing Booking Component (EJB)", 7, totalSteps);
                    status = bookingService.doLoginBooking(user, pwd);
                    if (status == RMIConnectionStatus.CONNECTED) {
                        notifyCallbackConsumer("Successfully initialized Booking Component (EJB)", 8, totalSteps);
                        notifyCallbackConsumer("Opening Application", 9, totalSteps);
                        if (callbackConsumer != null) executeOnFX(o -> callbackConsumer.setupFinished(disposables));
                    }
                }
            }
        } else {
            status = RMIConnectionStatus.NO_CONNECTION;
        }
        return status;
    }

    @Override
    public void close() {
    }

    @Override
    public void setCallbackConsumer(SetupCallback callbackConsumer) {
        this.callbackConsumer = callbackConsumer;
    }

    @Override
    public void setMsgTopics() {
        msgAsyncService.setTopics(getSubscribedTopics());
    }

    @Override
    public void initMsgAsync(String clientId, boolean isRemote) {
        new Thread(() -> {
            String ip = TCP_LOCAL;
            if (remote) ip = TCP_JENKINS;
            try {
                msgAsyncService.init(ip, clientId);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }, "Hedwig").start();
    }

    @Override
    public void setMsgNotificationPresenter(ApplicationMain presenter) {
        this.presenter = presenter;
    }

    public List<MsgTopicDTO> getSubscribedTopics() {
        return msgTopicService.getSubscribedTopics();
    }


    public void notifyCallbackConsumer(String text, int current, int total) {
        if (callbackConsumer != null) {
            executeOnFX(o -> callbackConsumer.onNextSetup(text, current, total));
        }
    }

    public void executeOnFX(Consumer<Object> consumer) {
        disposables.add(Observable.just(new Object()).subscribeOn(JavaFxScheduler.platform()).subscribe(consumer));
    }
}

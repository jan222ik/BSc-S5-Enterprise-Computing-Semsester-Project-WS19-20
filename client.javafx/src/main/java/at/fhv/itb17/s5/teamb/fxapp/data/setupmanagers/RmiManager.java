package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.msg.MsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.*;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import com.airhacks.afterburner.injection.Injector;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

import javax.jms.JMSException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RmiManager implements SetupManager {

    private static final int totalSteps = 9;
    public static final String TCP = "tcp://localhost:61616";

    private RMIController controller;
    private boolean isConnected = false;
    private SearchService searchService;
    private BookingService bookingService;
    private MsgTopicService msgTopicService;
    private SetupCallback callbackConsumer;
    private MsgAsyncService msgAsyncService;

    private List<Disposable> disposables;

    @Override
    public boolean create() {
        try {
            disposables = new LinkedList<>();
            controller = new RMIController();
            searchService = new RMISearchServiceImpl(controller);
            bookingService = new RMIBookingServiceImpl(controller);
            msgTopicService = new RMITopicServiceImpl(controller);
            msgAsyncService = new MsgAsyncServiceImpl();
            Injector.setModelOrService(SearchService.class, searchService);
            Injector.setModelOrService(BookingService.class, bookingService);
            Injector.setModelOrService(MsgTopicService.class, msgTopicService);
            Injector.setModelOrService(RMIController.class, controller);
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
            try {
                notifyCallbackConsumer("Connecting to Server (RMI)", 0, totalSteps);
                isConnected = controller.connect(host, port);
                if (isConnected) {
                    notifyCallbackConsumer("Connected to Server (RMI)", 1, totalSteps);
                    return RMIConnectionStatus.CONNECTED;
                }
                return RMIConnectionStatus.NO_CONNECTION;
            } catch (RemoteException e) {
                e.printStackTrace();
                return RMIConnectionStatus.NO_CONNECTION;
            }
        } else {
            return RMIConnectionStatus.NO_CONNECTION;
        }
    }

    @Override
    public RMIConnectionStatus authenticate(String user, String pwd) {
        RMIConnectionStatus status;
        notifyCallbackConsumer("Checking Connection Status (RMI)", 2, totalSteps);
        if (isConnected) {
            notifyCallbackConsumer("Initializing Search Component (RMI)", 3, totalSteps);
            status = searchService.init();
            if (status == RMIConnectionStatus.CONNECTED) {
                notifyCallbackConsumer("Successfully initialized Search Component (RMI)", 4, totalSteps);
                notifyCallbackConsumer("Initializing Messaging Component (RMI)", 5, totalSteps);
                status = msgTopicService.doLoginMsgTopic(user, pwd);
                if (status == RMIConnectionStatus.CONNECTED) {
                    notifyCallbackConsumer("Successfully initialized Messaging Component (RMI)", 6, totalSteps);
                    notifyCallbackConsumer("Initializing Booking Component (RMI)", 7, totalSteps);
                    status = bookingService.doLoginBooking(user, pwd);
                    if (status == RMIConnectionStatus.CONNECTED) {
                        notifyCallbackConsumer("Successfully initialized Booking Component (RMI)", 8, totalSteps);
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
        if (controller != null) {
            controller.stopRMI();
        }
    }

    @Override
    public void setCallbackConsumer(SetupCallback callbackConsumer) {
        this.callbackConsumer = callbackConsumer;
    }

    @Override
    public void setMsgTopics() {
        msgAsyncService.setTopics(getSubscribedTopics());
    }

    public void notifyCallbackConsumer(String text, int current, int total) {
        if (callbackConsumer != null) {
            executeOnFX(o -> callbackConsumer.onNextSetup(text, current, total));
        }
    }

    public void executeOnFX(Consumer<Object> consumer) {
        disposables.add(Observable.just(new Object()).subscribeOn(JavaFxScheduler.platform()).subscribe(consumer));
    }

    private List<MsgTopic> getSubscribedTopics() {
        return msgTopicService.getSubscribedTopics();
    }

    @Override
    public void initMsgAsync(String clientId) {
        new Thread(() -> {
            try {
                msgAsyncService.init(TCP, clientId);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }, "Hedwig").start();
    }
}

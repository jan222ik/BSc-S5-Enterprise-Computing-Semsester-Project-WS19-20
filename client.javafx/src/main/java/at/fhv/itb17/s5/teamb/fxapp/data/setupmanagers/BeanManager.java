package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBBookingServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBController;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBSearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBTopicServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.*;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import com.airhacks.afterburner.injection.Injector;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class BeanManager implements SetupManager {
    private static final int totalSteps = 9;

    private EJBController controller;
    private boolean isConnected = true;
    private SearchService searchService;
    private BookingService bookingService;
    private MsgTopicService msgTopicService;
    private SetupCallback callbackConsumer;

    private List<Disposable> disposables;

    @Override
    public boolean create() {
        try {
            disposables = new LinkedList<>();
            controller = new EJBController();
            searchService = new EJBSearchServiceImpl(controller);
            bookingService = new EJBBookingServiceImpl(controller);
            msgTopicService = new EJBTopicServiceImpl(controller);
            Injector.setModelOrService(SearchService.class, searchService);
            Injector.setModelOrService(BookingService.class, bookingService);
            Injector.setModelOrService(MsgTopicService.class, msgTopicService);
            Injector.setModelOrService(EJBController.class, controller);
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
            if (isConnected) {
                notifyCallbackConsumer("Connected to Server (EJB)", 1, totalSteps);
                return RMIConnectionStatus.CONNECTED;
            }
            return RMIConnectionStatus.NO_CONNECTION;
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
        if (controller != null) {
        }
    }

    @Override
    public void setCallbackConsumer(SetupCallback callbackConsumer) {
        this.callbackConsumer = callbackConsumer;
    }

    @Override
    public void setMsgTopics() {

    }

    @Override
    public void initMsgAsync(String clientIdm, boolean isRemote) {

    }

    @Override
    public void setMsgNotificationPresenter(ApplicationMain presenter) {

    }

    /*
    @Override
    public List<MsgTopic> getSubscribedTopics() {
        return msgTopicService.getSubscribedTopics();
    }
     */

    public void notifyCallbackConsumer(String text, int current, int total) {
        if (callbackConsumer != null) {
            executeOnFX(o -> callbackConsumer.onNextSetup(text, current, total));
        }
    }

    public void executeOnFX(Consumer<Object> consumer) {
        disposables.add(Observable.just(new Object()).subscribeOn(JavaFxScheduler.platform()).subscribe(consumer));
    }
}

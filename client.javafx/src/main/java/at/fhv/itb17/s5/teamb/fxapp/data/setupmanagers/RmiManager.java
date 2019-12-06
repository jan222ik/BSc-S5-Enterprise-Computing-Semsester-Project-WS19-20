package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIBookingServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIController;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMISearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMITopicServiceImpl;
import com.airhacks.afterburner.injection.Injector;

import java.rmi.RemoteException;

public class RmiManager implements SetupManager {

    private static final int totalSteps = 9;

    private RMIController controller;
    private boolean isConnected = false;
    private SearchService searchService;
    private BookingService bookingService;
    private MsgTopicService msgTopicService;
    private SetupCallback callbackConsumer;

    @Override
    public boolean create() {
        try {
            controller = new RMIController();
            searchService = new RMISearchServiceImpl(controller);
            bookingService = new RMIBookingServiceImpl(controller);
            msgTopicService = new RMITopicServiceImpl(controller);
            Injector.setModelOrService(SearchService.class, searchService);
            Injector.setModelOrService(BookingService.class, bookingService);
            Injector.setModelOrService(MsgTopicService.class, msgTopicService);
            Injector.setModelOrService(RMIController.class, controller);
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
                        if (callbackConsumer != null) callbackConsumer.setupFinished();
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
    
    public void notifyCallbackConsumer(String text, int current, int total) {
        if (callbackConsumer != null) {
            callbackConsumer.onNextSetup(text, current, total);
        }
    }
}

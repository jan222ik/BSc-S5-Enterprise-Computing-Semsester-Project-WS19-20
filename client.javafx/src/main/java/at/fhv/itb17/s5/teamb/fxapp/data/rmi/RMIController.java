package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.IConnectionFactoryRMI;
import at.fhv.itb17.s5.teamb.corestarter.EntryPointRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIController {

    private static final Logger logger = LogManager.getLogger(RMIController.class);


    private IConnectionFactoryRMI stub = null;
    private Registry registry = null;

    @SuppressWarnings("RedundantThrows")
    public RMIController() throws RemoteException {
        System.setSecurityManager(new SecManager());
    }

    @SuppressWarnings({"UnusedReturnValue", "RedundantThrows"})
    public boolean connect(String host, int port) throws RemoteException {
        logger.debug("RMI: Controller Connect");
        try {
            registry = LocateRegistry.getRegistry(host, port);
            if (registry == null) {
                logger.error("Registry is null");
            }
            stub = (IConnectionFactoryRMI) registry.lookup(EntryPointRMI.FACTORY_BIND_NAME);
            if (stub == null) {
                logger.error("Stub is null");
            }
            logger.debug("stub = {}", stub);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
        logger.debug("RMI Client Started");
        return true;
    }

    public SearchService createSearchService() throws RemoteException {
        logger.info("RMI: Creating SearchService");
        if (stub != null) {
            SearchService searchService = stub.createSearchService();
            logger.info("RMI: Created SearchService");
            return searchService;
        } else {
            logger.info("RMI: ERROR CREATION SearchService");
            return null;
        }
    }

    public BookingService createBookingService(IFrontEndClient frontEndClient, String username, String password) throws RemoteException {
        logger.info("RMI: Creating BookingService");
        if (stub == null) return null;
        BookingService bookingService = stub.createBookingService(frontEndClient, username, password);
        logger.debug("Created bookingService = {}", bookingService);
        return bookingService;
    }

    public void stopRMI() {
        registry = null;
    }

    public MsgTopicService createMsgTopicService(String username, String password) throws RemoteException {
        logger.info("RMI: Creating MsgTopicService");
        if (stub == null) return null;
        MsgTopicService topicService = stub.createTopicService(username, password);
        logger.debug("Created topicService = {}", topicService);
        return topicService;
    }

}

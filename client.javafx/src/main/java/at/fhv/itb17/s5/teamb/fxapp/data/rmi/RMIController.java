package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.EntryPointRMI;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.IConnectionFactoryRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIController {

    private static final Logger logger = LogManager.getLogger(RMIController.class);


    private IConnectionFactoryRMI stub;
    private Registry registry;

    public RMIController(String host, int port) throws RemoteException {
        System.setSecurityManager(new SecManager());
        try {
            registry = LocateRegistry.getRegistry(host, port);

            stub = (IConnectionFactoryRMI) registry.lookup(EntryPointRMI.FACTORY_BIND_NAME);
            System.out.println("stub = " + stub);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        logger.debug("RMI Client Started");
    }

    public SearchService createSearchService() throws RemoteException {
        logger.info("RMI: Created SearchService");
        return stub.createSearchService();
    }

    public BookingService createBookingService(IFrontEndClient frontEndClient, String username, String password) throws RemoteException {
        logger.info("RMI: Created BookingService");
        BookingService bookingService = stub.createBookingService(frontEndClient, username, password);
        System.out.println("bookingService = " + bookingService);
        return bookingService;
    }

    public void stopRMI() {
        registry = null;
    }
}

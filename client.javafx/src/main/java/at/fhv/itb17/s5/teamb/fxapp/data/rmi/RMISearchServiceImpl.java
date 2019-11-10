package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.FrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.EntryPointRMI;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.IConnectionFactoryRMI;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class RMISearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(RMISearchServiceImpl.class);

    private at.fhv.itb17.s5.teamb.core.controllers.general.SearchService remoteSearchService;
    private at.fhv.itb17.s5.teamb.core.controllers.general.BookingService remoteBookingService = null;

    private IConnectionFactoryRMI stub;

    public RMISearchServiceImpl(String host, int port) throws RemoteException {
        System.setSecurityManager(new SecManager());
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);

            stub = (IConnectionFactoryRMI) registry.lookup(EntryPointRMI.FACTORY_BIND_NAME);
            System.out.println("stub = " + stub);
            remoteSearchService = stub.createSearchService();

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        logger.debug("RMI Client Started");
    }

    public boolean doLoginBooking(String username, String password) {
        try {
            FrontEndClient client = new FrontEndClient();
            remoteBookingService = stub.createBookingService(client, username, password);
            if (remoteBookingService != null) {
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList<EventDTO> searchFor(String searchQuery) {
        logger.debug("Call Remote SearchService");
        try {
            return remoteSearchService.searchFor(searchQuery);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}

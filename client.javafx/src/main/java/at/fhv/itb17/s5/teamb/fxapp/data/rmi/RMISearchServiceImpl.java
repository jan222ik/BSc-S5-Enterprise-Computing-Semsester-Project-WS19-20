package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.controllers.rmi.EntryPointRMI;
import at.fhv.itb17.s5.teamb.controllers.rmi.IConnectionFactoryRMI;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.BrowserPresenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class RMISearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(RMISearchServiceImpl.class);

    private at.fhv.itb17.s5.teamb.controllers.SearchService remoteService;

    public RMISearchServiceImpl(String host, int port) {
        System.setSecurityManager(new SecManager());
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);

            IConnectionFactoryRMI stub = (IConnectionFactoryRMI) registry.lookup(EntryPointRMI.FACTORY_BIND_NAME);
            remoteService = stub.createSearchService();

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        logger.debug("RMI Client Started");
    }

    @Override
    public LinkedList<EventDTO> searchFor(String searchQuery) {
        logger.debug("Call Remote SearchService");
        return remoteService.searchFor(searchQuery);
    }
}

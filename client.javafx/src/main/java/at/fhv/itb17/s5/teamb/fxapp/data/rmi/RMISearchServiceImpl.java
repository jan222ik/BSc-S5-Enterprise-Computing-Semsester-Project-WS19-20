package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.LinkedList;

public class RMISearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(RMISearchServiceImpl.class);
    private RMIController rmi;
    private at.fhv.itb17.s5.teamb.core.controllers.general.SearchService remoteSearchService;

    public RMISearchServiceImpl(RMIController rmi) throws RemoteException {
        this.rmi = rmi;
    }

    public RMIConnectionStatus init() {
        logger.debug("Create SearchService");
        try {
            remoteSearchService = rmi.createSearchService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        logger.debug("SearchService: {}", remoteSearchService);
        return (remoteSearchService != null) ? RMIConnectionStatus.CONNECTED : RMIConnectionStatus.NO_CONNECTION;
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

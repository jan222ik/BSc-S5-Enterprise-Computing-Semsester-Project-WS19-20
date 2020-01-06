package at.fhv.itb17.s5.teamb.fxapp.data.ejb;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class EJBSearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(EJBSearchServiceImpl.class);
    private EJBController ejb;
    private at.fhv.itb17.s5.teamb.core.controllers.general.SearchService remoteSearchService;

    @SuppressWarnings({"RedundantThrows", "squid:RedundantThrowsDeclarationCheck"})
    public EJBSearchServiceImpl(EJBController ejb) throws RemoteException {
        this.ejb = ejb;
    }

    public RMIConnectionStatus init() {
        logger.debug("Create SearchService");
        try {
            remoteSearchService = ejb.createSearchService();
        } catch (NamingException e) {
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

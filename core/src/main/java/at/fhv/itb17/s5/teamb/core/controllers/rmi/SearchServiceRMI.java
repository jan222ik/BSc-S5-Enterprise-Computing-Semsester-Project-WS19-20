package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.EventMapper;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class SearchServiceRMI extends UnicastRemoteObject implements SearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceRMI.class);
    private SearchServiceCore coreSearch;

    public SearchServiceRMI(SearchServiceCore searchServiceCore) throws RemoteException {
        this.coreSearch = searchServiceCore;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public LinkedList<EventDTO> searchFor(String queryString) throws RemoteException {
        logger.debug(LogMarkers.RMI_CONTROLLER, "Invoked SearchString: {}", queryString);
        List<Event> events = coreSearch.searchFor(queryString);
        return new LinkedList<>(EventMapper.toDTOs(events));
    }

    @Override
    public Object updateTicketAvailability(Object ticket) throws RemoteException {
        return null;
    }
}

package at.fhv.itb17.s5.teamb.core.controllers.rest;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class SearchServiceREST implements SearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceREST.class);

    private SearchServiceCore coreSearch;
    private EntityDTORepo entityDTORepo;

    public SearchServiceREST(SearchServiceCore searchServiceCore, EntityDTORepo entityDTORepo) {
        this.coreSearch = searchServiceCore;
        this.entityDTORepo = entityDTORepo;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public LinkedList<EventDTO> searchFor(String queryString) {
        logger.debug(LogMarkers.RMI_CONTROLLER, "Invoked SearchString for REST: {}", queryString);
        List<Event> events = coreSearch.searchFor(queryString);
        return new LinkedList<>(entityDTORepo.toEventDTOs(events));
    }
}

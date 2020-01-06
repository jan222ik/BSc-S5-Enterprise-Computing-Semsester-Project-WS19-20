package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class SearchServiceEJB implements SearchService {
    private static final Logger logger = LogManager.getLogger(SearchServiceEJB.class);
    private SearchServiceCore coreSearch;
    private EntityDTORepo entityDTORepo;

    public SearchServiceEJB(SearchServiceCore searchServiceCore, EntityDTORepo entityDTORepo) {
        this.coreSearch = searchServiceCore;
        this.entityDTORepo = entityDTORepo;
    }

    public SearchServiceEJB() {
        CoreServiceInjector injector = CoreServiceInjectorImpl.getInstance(true);
        this.coreSearch = injector.getSearchServiceCore();
        this.entityDTORepo = injector.getEntityRepo();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public LinkedList<EventDTO> searchFor(String queryString) {
        logger.debug(LogMarkers.EJB_CONTROLLER, "Invoked SearchString: {}", queryString);
        List<Event> events = coreSearch.searchFor(queryString);
        return new LinkedList<>(entityDTORepo.toEventDTOs(events));
    }

}

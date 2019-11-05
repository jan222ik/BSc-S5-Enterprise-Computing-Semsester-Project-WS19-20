package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCoreImpl;
import at.fhv.itb17.s5.teamb.persistence.repository.EventRepository;

public class CoreServiceInjectorImpl implements CoreServiceInjector {
    private final EventRepository entityRepository = new EventRepository();
    private final SearchServiceCore searchServiceCore = new SearchServiceCoreImpl(entityRepository);

    public SearchServiceCore getSearchServiceCore() {
        return searchServiceCore;
    }
}

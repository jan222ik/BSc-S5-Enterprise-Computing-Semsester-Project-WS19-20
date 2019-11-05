package at.fhv.itb17.s5.teamb.core.domain.search;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.repository.EventRepository;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;

import java.util.List;

public class SearchServiceCoreImpl implements SearchServiceCore {

    private EventRepository eventRepository;
    private SearchParser searchParser = SearchParser.INSTANCE;

    public SearchServiceCoreImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> searchFor(String queryString) {
        List<SearchPair> search = searchParser.parseString(queryString).retrieveSearchPairs();
        return eventRepository.search(search);
    }
}

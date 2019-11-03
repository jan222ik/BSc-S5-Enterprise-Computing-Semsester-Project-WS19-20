package at.fhv.itb17.s5.teamb.core.search;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.repository.EventRepository;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;

import java.util.LinkedList;

public class SearchServiceCoreImpl implements SearchServiceCore {

    private EventRepository eventRepository;
    private SearchParser searchParser = SearchParser.INSTANCE;

    @Override
    public LinkedList<Event> searchFor(String queryString) {
        LinkedList<SearchPair> search = searchParser.parseString(queryString).retrieveSearchPairs();
        return eventRepository.search(search);
    }
}

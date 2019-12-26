package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;

import java.util.List;

public class EventRepository {

    private EntityRepository ep;

    public EventRepository(EntityRepository ep) {
        this.ep = ep;
    }

    public List<Event> search(List<SearchPair> search) {
        return ep.getAll(Event.class, search);
    }
}

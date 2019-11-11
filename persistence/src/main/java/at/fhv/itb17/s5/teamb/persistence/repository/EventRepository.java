package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClauseBuilder;
import at.fhv.itb17.s5.teamb.persistence.util.WhereCondition;

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

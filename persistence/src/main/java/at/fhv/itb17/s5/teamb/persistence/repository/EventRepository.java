package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClauseBuilder;
import at.fhv.itb17.s5.teamb.persistence.util.WhereCondition;

import java.util.List;

public class EventRepository {

    private EntityRepository ep = new EntityRepository();

    public List<Event> search(List<SearchPair> search) {
        WhereCondition build = new WhereClauseBuilder().build(search);
        return ep.getAll(Event.class, build);
    }
}

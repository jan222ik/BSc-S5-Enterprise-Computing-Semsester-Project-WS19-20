package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClauseBuilder;
import at.fhv.itb17.s5.teamb.persistence.util.WhereCondition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class EventRepository {
    private EntityRepository ep = new EntityRepository();
    public LinkedList<Event> search(LinkedList<SearchPair> search) {
        LinkedList<Event> resultSet = new LinkedList<>();
        WhereCondition build = new WhereClauseBuilder().equals("name", "Hugo").build();
        ep.getAll(Event.class, build);
        throw new NotImplementedException(); //TODO CREATE SEARCH QUERY
        //return resultSet;
    }
}

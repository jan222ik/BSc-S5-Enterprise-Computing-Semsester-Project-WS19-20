package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class EventRepository {
    public LinkedList<Event> search(LinkedList<SearchPair> search) {
        LinkedList<Event> resultSet = new LinkedList<>();
        throw new NotImplementedException(); //TODO CREATE SEARCH QUERY
        //return resultSet;
    }
}

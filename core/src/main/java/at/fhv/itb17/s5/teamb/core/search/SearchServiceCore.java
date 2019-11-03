package at.fhv.itb17.s5.teamb.core.search;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;

import java.util.LinkedList;

public interface SearchServiceCore {
    LinkedList<Event> searchFor(String queryString);
}

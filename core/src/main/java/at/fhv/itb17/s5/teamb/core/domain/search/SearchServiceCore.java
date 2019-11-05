package at.fhv.itb17.s5.teamb.core.domain.search;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;

import java.util.List;

public interface SearchServiceCore {
    List<Event> searchFor(String queryString);
}

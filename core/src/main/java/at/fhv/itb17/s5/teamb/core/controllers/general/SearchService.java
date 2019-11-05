package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;

import java.util.LinkedList;

public interface SearchService {
    LinkedList<EventDTO> searchFor(String queryString);
    Object updateTicketAvailability(Object ticket);
}

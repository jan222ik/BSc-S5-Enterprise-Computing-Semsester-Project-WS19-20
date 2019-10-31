package at.fhv.itb17.s5.teamb.controllers;

import java.util.LinkedList;

public interface SearchService {
    LinkedList<Object> searchFor(String queryString);
    Object updateTicketAvailability(Object ticket);
}

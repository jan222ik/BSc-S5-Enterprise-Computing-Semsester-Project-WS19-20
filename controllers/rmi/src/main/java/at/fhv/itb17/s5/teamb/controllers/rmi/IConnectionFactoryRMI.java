package at.fhv.itb17.s5.teamb.controllers.rmi;

import at.fhv.itb17.s5.teamb.controllers.SearchService;

import java.rmi.Remote;

public interface IConnectionFactoryRMI extends Remote {
    public SearchService createSearchService();
    public Object createBookingService();
}

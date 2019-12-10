package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface SearchService extends Remote {
    LinkedList<EventDTO> searchFor(String queryString) throws RemoteException;
}

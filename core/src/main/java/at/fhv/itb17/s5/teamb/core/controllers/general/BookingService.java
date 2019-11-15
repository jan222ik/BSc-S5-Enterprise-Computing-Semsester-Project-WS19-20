package at.fhv.itb17.s5.teamb.core.controllers.general;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookingService extends Remote {
    boolean bookTicket(Object obj) throws RemoteException;
}

package at.fhv.itb17.s5.teamb.core.controllers.general;

import java.rmi.Remote;

public interface BookingService extends Remote {
    boolean bookTicket(Object obj);
}

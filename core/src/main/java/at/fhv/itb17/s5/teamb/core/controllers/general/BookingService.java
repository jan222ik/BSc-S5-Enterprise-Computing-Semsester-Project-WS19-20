package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookingService extends Remote {
    boolean bookTickets(List<TicketDTO> ticketDTOs) throws RemoteException;
}

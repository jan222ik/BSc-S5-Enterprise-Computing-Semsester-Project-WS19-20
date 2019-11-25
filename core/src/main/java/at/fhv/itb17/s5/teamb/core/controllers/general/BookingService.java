package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookingService extends Remote {
    List<TicketDTO> bookTickets(List<TicketDTO> ticketDTOs) throws RemoteException;

    List<TicketDTO> reserveTickets(List<TicketDTO> ticketDTOs) throws RemoteException;
}

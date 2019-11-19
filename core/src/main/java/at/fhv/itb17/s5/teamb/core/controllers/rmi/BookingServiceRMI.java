package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BookingServiceRMI extends UnicastRemoteObject implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingServiceRMI.class);
    private BookingServiceCore bookingServiceCore;
    private ClientSessionRMI clientSessionRMI;
    private EntityDTORepo entityDTORepo;

    public BookingServiceRMI(BookingServiceCore bookingServiceCore, ClientSessionRMI client, EntityDTORepo entityDTORepo) throws RemoteException {
        this.bookingServiceCore = bookingServiceCore;
        this.clientSessionRMI = client;
        this.entityDTORepo = entityDTORepo;
    }

    @Override
    public boolean bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.RMI_CONTROLLER, "Invoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        //TODO BOOK FWD
        // 1. Convert to correct Entities.
        // 2. FWD Tickets.
        // 3, return successState
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(ticketDTOs.get(0) + " -> " + tickets.get(i));
        }
        List<Ticket> tickets1 = bookingServiceCore.bookTickets(tickets);
        return tickets1 != null; //TODO Returning Tickets would be better
    }
}

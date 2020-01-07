package at.fhv.itb17.s5.teamb.core.controllers.rest;

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
import java.util.List;

public class BookingServiceREST implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingServiceREST.class);

    private BookingServiceCore bookingServiceCore;
    private ClientSessionRMI clientSessionRMI;
    private EntityDTORepo entityDTORepo;

    public BookingServiceREST(BookingServiceCore bookingServiceCore, ClientSessionRMI client, EntityDTORepo entityDTORepo) {
        this.bookingServiceCore = bookingServiceCore;
        this.clientSessionRMI = client;
        this.entityDTORepo = entityDTORepo;
    }

    @Override
    public List<TicketDTO> bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.REST_SERVICE, "Invoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.bookTickets(tickets));
    }

    @Override
    public List<TicketDTO> reserveTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.REST_SERVICE, "Invoked Reserve: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.reserveTickets(tickets));
    }

    @Override
    public void setUserForEJB(String username, String password) throws RemoteException {
        //only used for ejb
    }
}

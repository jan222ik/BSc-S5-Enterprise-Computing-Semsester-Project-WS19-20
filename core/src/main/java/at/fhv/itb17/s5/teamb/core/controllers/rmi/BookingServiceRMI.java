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

@SuppressWarnings({"squid:S2160", "squid:S1948"})
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
    public List<TicketDTO> bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.RMI_CONTROLLER, "Invoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.bookTickets(tickets));
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public List<TicketDTO> reserveTickets(List<TicketDTO> ticketDTOs) throws RemoteException {
        logger.info(LogMarkers.RMI_CONTROLLER, "Invoked Reserve: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.reserveTickets(tickets));
    }

    @SuppressWarnings("squid:UnusedPrivateMethod") //Used to debug stuff
    private void logTicketsDiffs(List<TicketDTO> ticketDTOs, List<Ticket> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            logger.info("{} -> {}", ticketDTOs.get(i), tickets.get(i));
        }
    }
}

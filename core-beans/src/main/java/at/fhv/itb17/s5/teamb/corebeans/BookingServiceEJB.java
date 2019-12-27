package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.general.AuthManagerCore;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.commons.collections.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BookingServiceEJB implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingServiceEJB.class);

    private BookingServiceCore bookingServiceCore;
    private ClientSessionRMI clientSessionRMI;
    private EntityDTORepo entityDTORepo;

    public BookingServiceEJB(BookingServiceCore bookingServiceCore, ClientSessionRMI client, EntityDTORepo entityDTORepo) {
        this.bookingServiceCore = bookingServiceCore;
        this.clientSessionRMI = client;
        this.entityDTORepo = entityDTORepo;
    }

    public BookingServiceEJB() {
        CoreServiceInjector injector = CoreServiceInjectorImpl.getInstance(true);
        this.bookingServiceCore = injector.getBookingServiceCore();
        this.entityDTORepo = injector.getEntityRepo();
        this.clientSessionRMI = new ClientSessionRMI(null,null, null, injector.getAuthManagerCore().queryClient("admin"));
    }

    @Override
    public List<TicketDTO> bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.EJB_CONTROLLER, "Insvoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.bookTickets(tickets));
    }

    @Override
    public List<TicketDTO> reserveTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.EJB_CONTROLLER, "Invoked Reserve: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
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

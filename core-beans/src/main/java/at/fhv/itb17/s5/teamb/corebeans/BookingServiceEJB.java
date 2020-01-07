package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        this.clientSessionRMI = new ClientSessionRMI(null, null, null, injector.getAuthManagerCore().queryClient("admin"));
    }

    @Override
    public List<TicketDTO> bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.EJB_CONTROLLER, "Insvoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        AtomicReference<List<TicketDTO>> booked = new AtomicReference<>(entityDTORepo.toTicketDTOs(bookingServiceCore.bookTickets(tickets)));
        List<TicketDTO> bookedTickets = booked.get();
        if (bookedTickets == null || bookedTickets.isEmpty()) {
            Thread thread = new Thread(() -> {
                booked.set(entityDTORepo.toTicketDTOs(bookingServiceCore.bookTickets(tickets)));
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return booked.get();
    }

    @Override
    public List<TicketDTO> reserveTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.EJB_CONTROLLER, "Invoked Reserve: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        List<Ticket> tickets = entityDTORepo.toTickets(ticketDTOs, clientSessionRMI.getClient());
        return entityDTORepo.toTicketDTOs(bookingServiceCore.reserveTickets(tickets));
    }

    @Override
    public void setUserForEJB(String username, String password) throws RemoteException {
        CoreServiceInjector injector = CoreServiceInjectorImpl.getInstance(true);
        this.clientSessionRMI = new ClientSessionRMI(username, "", null, injector.getAuthManagerCore().checkAndQuery(username, password));
    }

    @SuppressWarnings("squid:UnusedPrivateMethod") //Used to debug stuff
    private void logTicketsDiffs(List<TicketDTO> ticketDTOs, List<Ticket> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            logger.info("{} -> {}", ticketDTOs.get(i), tickets.get(i));
        }
    }
}

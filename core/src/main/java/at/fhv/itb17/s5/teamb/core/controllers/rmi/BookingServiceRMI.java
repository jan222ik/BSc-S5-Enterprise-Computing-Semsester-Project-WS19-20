package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
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

    public BookingServiceRMI(BookingServiceCore bookingServiceCore, ClientSessionRMI client) throws RemoteException {
        this.bookingServiceCore = bookingServiceCore;
        this.clientSessionRMI = client;
    }

    @Override
    public boolean bookTickets(List<TicketDTO> ticketDTOs) {
        logger.info(LogMarkers.RMI_CONTROLLER, "Invoked Booking: Size:{} for the client {}", ticketDTOs.size(), clientSessionRMI);
        //TODO BOOK FWD
        // 1. Convert to correct Entities
        // 2. FWD Tickets
        // 3, return successState
        entityDTORepo.toTickets(ticketDTOs);
        //bookingServiceCore.
        return true;
    }
}

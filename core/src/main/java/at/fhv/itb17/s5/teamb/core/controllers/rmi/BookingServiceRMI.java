package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BookingServiceRMI extends UnicastRemoteObject implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingServiceRMI.class);
    private BookingServiceCore bookingServiceCore;
    private ClientSessionRMI clientSessionRMI;

    public BookingServiceRMI(BookingServiceCore bookingServiceCore, ClientSessionRMI client) throws RemoteException {
        this.bookingServiceCore = bookingServiceCore;
        this.clientSessionRMI = client;
    }

    @Override
    public boolean bookTicket(Object obj) {
        logger.debug(LogMarkers.RMI_CONTROLLER, "Invoked Booking: {} for the client {}", obj, clientSessionRMI);
        return false;
    }
}

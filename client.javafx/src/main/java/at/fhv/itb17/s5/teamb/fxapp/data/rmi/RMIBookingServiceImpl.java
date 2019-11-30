package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.FrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIBookingServiceImpl implements BookingService {

    private static final Logger logger = LogManager.getLogger(RMIBookingServiceImpl.class);
    private static final String RMI_REMOTE_EXCEPTION = "RMI Remote Exception";
    private RMIController rmi;
    private IFrontEndClient client;
    private at.fhv.itb17.s5.teamb.core.controllers.general.BookingService remoteBookingService;

    public RMIBookingServiceImpl(RMIController rmi) throws RemoteException {
        this.rmi = rmi;
    }


    @Override
    public RMIConnectionStatus doLoginBooking(String username, String password) {
        try {
            client = new FrontEndClient();
            remoteBookingService = rmi.createBookingService(client, username, password);
            if (remoteBookingService != null) {
                return RMIConnectionStatus.CONNECTED;
            } else {
                return RMIConnectionStatus.CREDENTIALS_INVALID;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            logger.error(RMI_REMOTE_EXCEPTION);
        }
        return RMIConnectionStatus.NO_CONNECTION;
    }

    @Override
    public void logout() {
        remoteBookingService = null;
        try {
            UnicastRemoteObject.unexportObject(client, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    public List<TicketDTO> book(List<TicketDTO> ticketDTOs) {
        logger.info("Booking {} tickets", ticketDTOs.size());
        if (remoteBookingService != null) {
            try {
                return remoteBookingService.bookTickets(ticketDTOs);
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error(RMI_REMOTE_EXCEPTION);
                return null;
            }
        } else {
            logger.fatal("Not logged in! - No Service");
            return null;
        }
    }

    @Override
    public List<TicketDTO> reserve(List<TicketDTO> ticketDTOs) {
        logger.info("Reserve {} tickets", ticketDTOs.size());
        if (remoteBookingService != null) {
            try {
                return remoteBookingService.reserveTickets(ticketDTOs);
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.error(RMI_REMOTE_EXCEPTION);
                return null;
            }
        } else {
            logger.fatal("Not logged in! - No Service");
            return null;
        }
    }
}

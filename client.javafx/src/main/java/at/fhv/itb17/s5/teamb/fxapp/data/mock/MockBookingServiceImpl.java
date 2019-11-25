package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;

import java.util.List;

public class MockBookingServiceImpl implements at.fhv.itb17.s5.teamb.fxapp.data.BookingService {
    @Override
    public RMIConnectionStatus doLoginBooking(String username, String password) {
        return RMIConnectionStatus.CONNECTED;
    }

    @Override
    public void logout() {

    }

    @Override
    public List<TicketDTO> book(List<TicketDTO> ticketDTOs) {
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> reserve(List<TicketDTO> ticketDTOs) {
        return ticketDTOs;
    }
}

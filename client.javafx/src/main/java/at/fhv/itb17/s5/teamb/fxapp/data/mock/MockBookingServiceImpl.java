package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;

import java.util.List;

public class MockBookingServiceImpl implements at.fhv.itb17.s5.teamb.fxapp.data.BookingService {
    @Override
    public boolean doLoginBooking(String username, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean book(List<TicketDTO> ticketDTOs) {
        return true;
    }
}

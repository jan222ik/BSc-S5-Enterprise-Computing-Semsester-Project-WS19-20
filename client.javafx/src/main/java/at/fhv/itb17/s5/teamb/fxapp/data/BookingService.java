package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;

import java.util.List;

public interface BookingService {
    boolean doLoginBooking(String username, String password);

    void logout();

    boolean book(List<TicketDTO> ticketDTOs);
}

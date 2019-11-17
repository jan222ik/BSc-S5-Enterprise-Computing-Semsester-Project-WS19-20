package at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;

import java.util.List;

public interface CatItemView {
    int getTicketAmount();

    EvCategoryInterfaceDTO getCat();

    List<TicketDTO> getTickets(EventDTO eventDTO, EvOccurrenceDTO occ);
}

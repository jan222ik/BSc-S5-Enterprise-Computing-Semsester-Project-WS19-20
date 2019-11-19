package at.fhv.itb17.s5.teamb.dtos.mapper;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    private TicketMapper() {
    }

    public static List<TicketDTO> toDTOs(@NotNull List<Ticket> tickets) {
        return tickets.stream().map(TicketMapper::toDTO).collect(Collectors.toList());
    }

    @NotNull
    public static TicketDTO toDTO(@NotNull Ticket e) {
        return new TicketDTO(e.getTicketId(), EventMapper.toDTO(e.getBookedEvent()), EvOccurrenceMapper.toDTO(e.getBookedOccurrence()), EvCategoryMapper.toDTO(e.getBookedCategory()), LocationRowMapper.toDTO(e.getBookedRow()), LocationSeatMapper.toDTO(e.getBookedSeat()));
    }

}

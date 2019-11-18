package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private Long id;
    private EventDTO eventDTO;
    private EvOccurrenceDTO occ;
    private EvCategoryInterfaceDTO cat;
    private LocationRowDTO row;
    private LocationSeatDTO seat;

    public TicketDTO(Long id, EventDTO eventDTO, EvOccurrenceDTO occ, EvCategoryInterfaceDTO cat, LocationRowDTO row, LocationSeatDTO seat) {
        this.id = id;
        this.eventDTO = eventDTO;
        this.occ = occ;
        this.cat = cat;
        this.row = row;
        this.seat = seat;
    }

    public TicketDTO(EventDTO eventDTO, EvOccurrenceDTO occ, EvCategorySeatsDTO cat, LocationRowDTO row, LocationSeatDTO seat) {
        this.eventDTO = eventDTO;
        this.occ = occ;
        this.cat = cat;
        this.row = row;
        this.seat = seat;
    }

    public TicketDTO(EventDTO eventDTO, EvOccurrenceDTO occ, EvCategoryFreeDTO cat) {
        this.eventDTO = eventDTO;
        this.occ = occ;
        this.cat = cat;
        this.seat = null;
        this.row = null;
    }

    public Long getId() {
        return id;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public EvOccurrenceDTO getOcc() {
        return occ;
    }

    public EvCategoryInterfaceDTO getCat() {
        return cat;
    }

    public LocationRowDTO getRow() {
        return row;
    }

    public LocationSeatDTO getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", eventDTO=" + eventDTO +
                ", occ=" + occ +
                ", cat=" + cat +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }
}

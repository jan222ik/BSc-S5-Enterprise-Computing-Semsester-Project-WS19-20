package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private Integer id;
    private EventDTO eventDTO;
    private EvOccurrenceDTO occ;
    private EvCategoryInterface cat;
    private LocationRowDTO row;
    private LocationSeatDTO seat;

    public TicketDTO(Integer id, EventDTO eventDTO, EvOccurrenceDTO occ, EvCategoryInterface cat, LocationRowDTO row, LocationSeatDTO seat) {
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

    public Integer getId() {
        return id;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public EvOccurrenceDTO getOcc() {
        return occ;
    }

    public EvCategoryInterface getCat() {
        return cat;
    }

    public LocationRowDTO getRow() {
        return row;
    }

    public LocationSeatDTO getSeat() {
        return seat;
    }
}

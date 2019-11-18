package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.EventMapper;
import at.fhv.itb17.s5.teamb.dtos.mapper.TicketMapper;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.entities.TicketStates;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDTORepoImpl implements EntityDTORepo {
    private HashMap<TicketDTO, Ticket> tickets;
    private HashMap<EventDTO, Event> events;


    @Override
    public EventDTO toEventDTO(Event event) {
        EventDTO eventDTO = EventMapper.toDTO(event);
        events.put(eventDTO, event);
        return eventDTO;
    }

    @Override
    public Event toEvent(EventDTO eventDTO) {
        if (events.containsKey(eventDTO)) {
            return events.get(eventDTO);
        }
        return null;
    }

    @Override
    public List<EventDTO> toEventDTOs(List<Event> event) {
        return event.stream().map(this::toEventDTO).collect(Collectors.toList());
    }

    @Override
    public List<Event> toEvents(List<EventDTO> eventDTOs) {
        return eventDTOs.stream().map(this::toEvent).collect(Collectors.toList());
    }

    @Override
    public TicketDTO toTicketDTO(Ticket ticket) {
        TicketDTO dto = TicketMapper.toDTO(ticket);
        tickets.put(dto, ticket);
        return dto;
    }

    @Override
    public Ticket toTicket(TicketDTO ticketDTO, Client client) {
        if (ticketDTO.getId() == null) {
            Event evt = toEvent(ticketDTO.getEventDTO());
            EventOccurrence occ = toOcc(evt, ticketDTO.getOcc());
            EventCategory cat = toCat(occ, ticketDTO.getCat());
            LocationRow row = toRow(cat, ticketDTO.getRow());
            LocationSeat seat = toSeat(row, ticketDTO.getSeat());
            new Ticket(client, TicketStates.FREE, evt, occ, cat, row, seat);
        } else {
            return tickets.getOrDefault(ticketDTO, null);
        }
        return null;
    }

    private LocationSeat toSeat(LocationRow row, LocationSeatDTO seat) {
        if (row != null && seat != null) {
            List<LocationSeat> l = row.getSeats().stream().filter(e -> e.getSeatId().equals(seat.getSeatId())).collect(Collectors.toList());
            return (!l.isEmpty()) ? l.get(0) : null;
        } else {
            return null;
        }
    }


    private LocationRow toRow(EventCategory cat, LocationRowDTO row) {
        if (cat != null && row != null) {
            List<LocationRow> l = cat.getSeatingRows().stream().filter(e -> e.getRowId().equals(row.getRowId())).collect(Collectors.toList());
            return (!l.isEmpty()) ? l.get(0) : null;
        } else {
            return null;
        }
    }


    private EventCategory toCat(EventOccurrence occ, EvCategoryInterfaceDTO catDto) {
        if (occ != null && catDto != null) {
            List<EventCategory> l = occ.getPriceCategories().stream().filter(e -> e.getEventCategoryId().equals(catDto.getEventCategoryId())).collect(Collectors.toList());
            return (!l.isEmpty()) ? l.get(0) : null;
        } else {
            return null;
        }
    }


    private EventOccurrence toOcc(Event event, EvOccurrenceDTO occDTO) {
        if (event != null && occDTO != null) {
            List<EventOccurrence> l = event.getOccurrences().stream().filter(eventOccurrence -> eventOccurrence.getOccurrenceId().equals(occDTO.getOccurrenceId())).collect(Collectors.toList());
            return (!l.isEmpty()) ? l.get(0) : null;
        } else {
            return null;
        }
    }

    @Override
    public List<TicketDTO> toTicketDTOs(List<Ticket> tickets) {
        return tickets.stream().map(this::toTicketDTO).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> toTickets(List<TicketDTO> ticketDTOs, Client client) {
        return ticketDTOs.stream().map((TicketDTO ticketDTO) -> toTicket(ticketDTO, client)).collect(Collectors.toList());
    }
}

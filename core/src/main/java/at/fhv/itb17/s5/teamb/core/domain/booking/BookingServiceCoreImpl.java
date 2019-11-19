package at.fhv.itb17.s5.teamb.core.domain.booking;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.entities.TicketStates;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingServiceCoreImpl implements BookingServiceCore {

    private TicketRepository ticketRepository;

    @Inject
    public BookingServiceCoreImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket bookTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat) {
        return handleTicket(client, TicketStates.PAID, event, occ, cat, null, null);
    }

    @Override
    public Ticket bookTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat, LocationRow row, LocationSeat seat) {
        return handleTicket(client, TicketStates.PAID, event, occ, cat, row, seat);
    }

    @Override
    public List<Ticket> bookTickets(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull List<CatRowSeatWrapper> catRowSeatWrappers) {
        return handleTickets(client, TicketStates.PAID, event, occ, catRowSeatWrappers);
    }

    @Override
    public Ticket reserveTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat) {
        return handleTicket(client, TicketStates.RESERVED, event, occ, cat, null, null);
    }

    @Override
    public Ticket reserveTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat, LocationRow row, LocationSeat seat) {
        return handleTicket(client, TicketStates.RESERVED, event, occ, cat, row, seat);
    }

    @Override
    public List<Ticket> reserveTickets(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull List<CatRowSeatWrapper> catRowSeatWrappers) {
        return handleTickets(client, TicketStates.RESERVED, event, occ, catRowSeatWrappers);
    }

    @Nullable
    @Override
    public List<Ticket> bookTickets(List<Ticket> tickets) {
        tickets.forEach(e -> e.setState(TicketStates.PAID));
        List<List<Ticket>> listList = getTicketsSortedAfterEventAndOccAndCat(tickets);
        return listList.stream().flatMap(list -> Optional.ofNullable(ticketRepository.bookIfFree(list)).orElse(new LinkedList<>()).stream()).collect(Collectors.toList());
    }

    @Nullable
    private Ticket handleTicket(Client client, TicketStates state, Event event, EventOccurrence occ, EventCategory cat, LocationRow row, LocationSeat seat) {
        Ticket ticket = new Ticket(client, state, event, occ, cat, row, seat);
        return ticketRepository.bookIfFree(ticket);
    }

    @Nullable
    private List<Ticket> handleTickets(Client client, TicketStates state, Event event, EventOccurrence occ, @NotNull List<CatRowSeatWrapper> catRowSeatWrappers) {
        List<Ticket> tickets = new ArrayList<>(catRowSeatWrappers.size());
        for (CatRowSeatWrapper wrapper : catRowSeatWrappers) {
            tickets.add(new Ticket(client, state, event, occ, wrapper.getCat(), wrapper.getRow(), wrapper.getSeat()));
        }
        return ticketRepository.bookIfFree(tickets);
    }

    public List<List<Ticket>> getTicketsSortedAfterEventAndOccAndCat(@NotNull List<Ticket> tickets) {
        HashMap<Event, HashMap<EventOccurrence, HashMap<EventCategory, List<Ticket>>>> evMap = new HashMap<>();
        for (Ticket ticket : tickets) {
            HashMap<EventOccurrence, HashMap<EventCategory, List<Ticket>>> occMap;
            if (!evMap.containsKey(ticket.getBookedEvent())) {
                occMap = new HashMap<>();
                evMap.put(ticket.getBookedEvent(), occMap);
            } else {
                occMap = evMap.get(ticket.getBookedEvent());
            }
            HashMap<EventCategory, List<Ticket>> catMap;
            if (occMap.containsKey(ticket.getBookedOccurrence())) {
                catMap = occMap.get(ticket.getBookedOccurrence());
            } else {
                catMap = new HashMap<>();
                occMap.put(ticket.getBookedOccurrence(), catMap);
            }
            if (catMap.containsKey(ticket.getBookedCategory())) {
                List<Ticket> ticketDTOS = catMap.get(ticket.getBookedCategory());
                ticketDTOS.add(ticket);
            } else {
                catMap.put(ticket.getBookedCategory(), new LinkedList<>(Collections.singletonList(ticket)));
            }
        }
        return evMap.values().stream()
                .flatMap(
                        (HashMap<EventOccurrence, HashMap<EventCategory, List<Ticket>>> occMap) ->
                                occMap.values().stream()
                                        .flatMap((HashMap<EventCategory, List<Ticket>> catMap) ->
                                                catMap.values().stream()
                                        )
                ).collect(Collectors.toList());
    }
}

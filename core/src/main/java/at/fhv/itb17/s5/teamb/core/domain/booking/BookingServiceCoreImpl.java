package at.fhv.itb17.s5.teamb.core.domain.booking;

import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.entities.TicketStates;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
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

    @Nullable
    @Override
    public List<Ticket> bookTickets(List<Ticket> tickets) {
        tickets.forEach(e -> e.setState(TicketStates.PAID));
        List<List<Ticket>> listList = getTicketsSortedAfterEventAndOccAndCat(tickets);
        return listList.stream().flatMap(list -> Optional.ofNullable(ticketRepository.bookIfFree(list)).orElse(new LinkedList<>()).stream()).collect(Collectors.toList());
    }

    @Nullable
    @Override
    public List<Ticket> reserveTickets(List<Ticket> tickets) {
        tickets.forEach(e -> e.setState(TicketStates.RESERVED));
        List<List<Ticket>> listList = getTicketsSortedAfterEventAndOccAndCat(tickets);
        return listList.stream().flatMap(list -> Optional.ofNullable(ticketRepository.bookIfFree(list)).orElse(new LinkedList<>()).stream()).collect(Collectors.toList());
    }

    private List<List<Ticket>> getTicketsSortedAfterEventAndOccAndCat(@NotNull List<Ticket> tickets) {
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

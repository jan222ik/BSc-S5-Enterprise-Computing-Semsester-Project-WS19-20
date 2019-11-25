package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CartVM implements ViewModel {
    private List<TicketDTO> tickets;
    private BookingService bookingService;

    public CartVM(BookingService bookingService) {
        this.bookingService = bookingService;
        tickets = new LinkedList<>();
    }

    public void addTickets(List<TicketDTO> tickets) {
        this.tickets.addAll(tickets);
    }

    public void clear() {
        tickets.clear();
    }

    public void removeTicket(TicketDTO ticketDTO) {
        //TODO
    }

    @Nullable
    public Boolean book() {
        return processChanges(bookingService.book(tickets));
    }

    @Nullable
    public Boolean reserve() {
        return processChanges(bookingService.reserve(tickets));
    }

    @Nullable
    @SuppressWarnings("squid:2447")
    private Boolean processChanges(List<TicketDTO> bookedTickets) {
        if (bookedTickets != null) {
            if (bookedTickets.size() == tickets.size()) {
                this.clear();
                return true;
            } else {
                HashSet<TicketDTO> usedDTOS = new HashSet<>();
                tickets = tickets.stream().filter(ticketDTO -> {
                    for (TicketDTO bookedTicket : bookedTickets) {
                        if (!usedDTOS.contains(bookedTicket) && ticketDTO.valueEqual(bookedTicket)) {
                            usedDTOS.add(bookedTicket);
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
                return false;
            }
        } else {
            return null;
        }
    }


    /**
     * @return Listing of Tickets collected on its Event, Occurrence and Category.
     */
    public List<List<TicketDTO>> getTicketsSortedAfterEventAndOcc() {
        HashMap<EventDTO, HashMap<EvOccurrenceDTO, HashMap<EvCategoryInterfaceDTO, List<TicketDTO>>>> evMap = new HashMap<>();
        for (TicketDTO ticket : tickets) {
            HashMap<EvOccurrenceDTO, HashMap<EvCategoryInterfaceDTO, List<TicketDTO>>> occMap;
            if (!evMap.containsKey(ticket.getEventDTO())) {
                occMap = new HashMap<>();
                evMap.put(ticket.getEventDTO(), occMap);
            } else {
                occMap = evMap.get(ticket.getEventDTO());
            }
            HashMap<EvCategoryInterfaceDTO, List<TicketDTO>> catMap;
            if (occMap.containsKey(ticket.getOcc())) {
                catMap = occMap.get(ticket.getOcc());
            } else {
                catMap = new HashMap<>();
                occMap.put(ticket.getOcc(), catMap);
            }
            if (catMap.containsKey(ticket.getCat())) {
                List<TicketDTO> ticketDTOS = catMap.get(ticket.getCat());
                ticketDTOS.add(ticket);
            } else {
                catMap.put(ticket.getCat(), new LinkedList<>(Collections.singletonList(ticket)));
            }
        }
        return evMap.values().stream()
                .flatMap(
                        (HashMap<EvOccurrenceDTO, HashMap<EvCategoryInterfaceDTO, List<TicketDTO>>> occMap) ->
                                occMap.values().stream()
                                        .flatMap((HashMap<EvCategoryInterfaceDTO, List<TicketDTO>> catMap) ->
                                                catMap.values().stream()
                                        )
                ).collect(Collectors.toList());
    }
}

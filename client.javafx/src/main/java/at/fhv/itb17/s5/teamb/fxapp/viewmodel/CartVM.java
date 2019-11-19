package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;

import java.util.Collections;
import java.util.HashMap;
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

    public boolean book() {
        boolean book = bookingService.book(tickets);
        if (book) {
            this.clear();
        }
        return book;
    }


    /**
     * It is pure magic.
     * Authors: U may address us as grand wizards: Matthias and Janik.
     *
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

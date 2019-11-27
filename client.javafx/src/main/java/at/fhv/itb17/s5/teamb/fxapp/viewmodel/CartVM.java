package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
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
        throw new NotImplementedException();
        //TODO If tickets should be deletable before purchase
    }

    @Nullable
    public Boolean book() {
        return processChanges(bookingService.book(getTicketsSortedAfterEventAndOcc().stream().flatMap(Collection::stream).collect(Collectors.toList())));
    }

    @Nullable
    public Boolean reserve() {
        return processChanges(bookingService.reserve(getTicketsSortedAfterEventAndOcc().stream().flatMap(Collection::stream).collect(Collectors.toList())));
    }

    @Contract("null -> null")
    @Nullable
    @SuppressWarnings("squid:S2447")
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
                return tickets.isEmpty();
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
        List<TicketDTO> seatTickets = new LinkedList<>();
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
            EvCategoryInterfaceDTO cat = ticket.getCat();
            if (!catMap.containsKey(cat)) {
                catMap.put(cat, new LinkedList<>());
            }
            if (catMap.containsKey(cat)) {
                List<TicketDTO> ticketDTOS = catMap.get(cat);
                if (cat instanceof EvCategorySeatsDTO) {
                    seatTickets.add(ticket);
                } else {
                    ticketDTOS.add(ticket);
                }
            }
        }

        Map<Long, List<TicketDTO>> catSorted = tickets.stream().collect(Collectors.groupingBy(it -> it.getEventDTO().getEventId(), Collectors.toList()))
                .values().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(it -> it.getOcc().getOccurrenceId(), Collectors.toList()))
                .values().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(it -> it.getCat().getEventCategoryId(), Collectors.toList()));
        Map<? extends Class<? extends EvCategoryInterfaceDTO>, List<TicketDTO>> catSortedbyClassType = catSorted.values().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(it -> it.getCat().getClass()));
        Map<Long, List<TicketDTO>> catSortedMod = new HashMap<>();
        catSortedbyClassType.forEach((BiConsumer<Class<? extends EvCategoryInterfaceDTO>, List<TicketDTO>>) (aClass, ticketDTOS) -> {
            if (aClass == EvCategorySeatsDTO.class) {
                ticketDTOS.stream()
                        .collect(Collectors.groupingBy(it -> it.getRow().getRowIdentifier(), Collectors.toList()))
                        .values().stream().flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(it -> it.getSeat().getSeatIdentifier(), Collectors.toList()))
                        .values().stream().flatMap(Collection::stream).collect(Collectors.groupingBy((TicketDTO it) -> it.getCat().getEventCategoryId())).forEach(catSortedMod::put);
            } else {
                catSortedMod.put(ticketDTOS.get(0).getCat().getEventCategoryId(), ticketDTOS);
            }
        });
        List<List<TicketDTO>> collect = new ArrayList<>(catSortedMod.values());



        List<TicketDTO> collect2 = seatTickets.stream()
                .collect(Collectors.groupingBy(it -> it.getRow().getRowIdentifier(), Collectors.toList()))
                .values().stream().flatMap(Collection::stream)
                .collect(Collectors.groupingBy(it -> it.getSeat().getSeatIdentifier(), Collectors.toList()))
                .values().stream().flatMap(list -> Stream.of(list.get(0))).collect(Collectors.toList());
        for (TicketDTO ticket : collect2) {
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
            EvCategoryInterfaceDTO cat = ticket.getCat();
            if (!catMap.containsKey(cat)) {
                catMap.put(cat, new LinkedList<>());
            }
            if (catMap.containsKey(cat)) {
                List<TicketDTO> ticketDTOS = catMap.get(cat);
                ticketDTOS.add(ticket);
            }
        }
        List<List<TicketDTO>> collect1 = evMap.values().stream()
                .flatMap(
                        (HashMap<EvOccurrenceDTO, HashMap<EvCategoryInterfaceDTO, List<TicketDTO>>> occMap) ->
                                occMap.values().stream()
                                        .flatMap((HashMap<EvCategoryInterfaceDTO, List<TicketDTO>> catMap) ->
                                                catMap.values().stream()
                                        )
                ).collect(Collectors.toList());
        System.out.println("collect = " + collect.size());
        System.out.println(collect);
        boolean equals = (collect.size() == collect1.size());
        System.out.println("equals = " + equals);
        return collect1;
    }
}

package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
        throw new NotImplementedException("TODO: If tickets should be deletable before purchase");
        //TODO If tickets should be deletable before purchase
    }

    @Nullable
    public Boolean book() {
        return processChanges(bookingService.book(getTicketsByCategorySeatDistinct().stream().flatMap(Collection::stream).collect(Collectors.toList())));
    }

    @Nullable
    public Boolean reserve() {
        return processChanges(bookingService.reserve(getTicketsByCategorySeatDistinct().stream().flatMap(Collection::stream).collect(Collectors.toList())));
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
     * @return Listing of Tickets for each existing category reduced to distinct row seat combination for SeatCat.
     */
    public List<List<TicketDTO>> getTicketsByCategorySeatDistinct() {
        Map<Long, List<TicketDTO>> catSorted = tickets.stream()
                .collect(Collectors.groupingBy(it -> it.getEventDTO().getEventId(), Collectors.toList())).values().stream().flatMap(Collection::parallelStream)
                .collect(Collectors.groupingBy(it -> it.getOcc().getOccurrenceId(), Collectors.toList())).values().stream().flatMap(Collection::parallelStream)
                .collect(Collectors.groupingBy(it -> it.getCat().getEventCategoryId(), Collectors.toList()));
        Map<Long, List<TicketDTO>> catSortedMod = new HashMap<>();
        catSorted.values().stream().flatMap(Collection::stream)
                .collect(Collectors.groupingBy(it -> it.getCat().getClass()))
                .forEach((BiConsumer<Class<? extends EvCategoryInterfaceDTO>, List<TicketDTO>>) (aClass, ticketDTOS) -> {
                    if (aClass == EvCategorySeatsDTO.class) {
                        ticketDTOS.stream()
                                .collect(Collectors.groupingBy(it -> it.getRow().getRowId(), Collectors.toList())).values().stream().flatMap(Collection::stream)
                                .collect(Collectors.groupingBy(it -> it.getSeat().getSeatId(), Collectors.toList())).values().stream().flatMap(Collection::stream)
                                .collect(Collectors.groupingBy((TicketDTO it) -> it.getCat().getEventCategoryId()))
                                .forEach(catSortedMod::put);
                    } else {
                        catSortedMod.put(ticketDTOS.get(0).getCat().getEventCategoryId(), ticketDTOS);
                    }
                });
        return new ArrayList<>(catSortedMod.values());
    }
}

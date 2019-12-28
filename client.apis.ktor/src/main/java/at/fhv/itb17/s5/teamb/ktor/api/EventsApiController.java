package at.fhv.itb17.s5.teamb.ktor.api;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rest.SearchServiceREST;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.ktor.model.BookingResponse;
import at.fhv.itb17.s5.teamb.ktor.model.PaymentInfo;
import at.fhv.itb17.s5.teamb.ktor.model.PaymentProcessor;
import at.fhv.itb17.s5.teamb.ktor.model.PaymentProcessorMock;
import at.fhv.itb17.s5.teamb.ktor.model.RowSeat;
import at.fhv.itb17.s5.teamb.ktor.model.TicketOrder;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.repository.ClientRepository;
import at.fhv.itb17.s5.teamb.persistence.search.SearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);


    private final PaymentProcessor paymentProcessor;
    private CoreServiceInjector injector;


    public EventsApiController(CoreServiceInjector injector) {
        this.injector = injector;
        paymentProcessor = new PaymentProcessorMock();
    }

    public ResponseEntity<List<BookingResponse>> bookTicket(Long eventID, Long occID, Long catID, TicketOrder body) {
        System.out.println("eventID = [" + eventID + "], occID = [" + occID + "], catID = [" + catID + "], body = [" + body + "]");
        try {
            EntityDTORepo entityRepo = injector.getEntityRepo();
            ClientRepository clientRepo = injector.getClientRepo();
            LinkedList<TicketDTO> ticketDTOS = new LinkedList<>();
            EventDTO evt = entityRepo.getEventDTOByID(eventID);
            EvOccurrenceDTO occ = evt.getOccurrences().stream().filter(e -> e.getOccurrenceId() == occID).findFirst().orElseThrow(() -> new NotFoundException("OCCId not found"));
            EvCategoryInterfaceDTO cat = occ.getPriceCategories().stream().filter(e -> e.getEventCategoryId() == catID).findFirst().orElseThrow(() -> new NotFoundException("CatId not found"));
            boolean isSeat = false;
            if (body.getRowseats() != null && !body.getRowseats().isEmpty()) {
                isSeat = true;
                EvCategorySeatsDTO castCat = (EvCategorySeatsDTO) cat;
                for (RowSeat rowSeat : body.getRowseats()) {
                    LocationRowDTO row = castCat.getSeatingRows().stream().filter(e -> e.getRowId() == rowSeat.getRowID()).findFirst().orElseThrow(() -> new NotFoundException("RowId not found"));
                    LocationSeatDTO seat = row.getSeats().stream().filter(e -> e.getSeatId() == rowSeat.getSeatID()).findFirst().orElseThrow(() -> new NotFoundException("SeatId not found"));
                    ticketDTOS.add(new TicketDTO(evt, occ, castCat, row, seat));
                }
            } else {
                for (int i = 0; i < body.getAmount(); i++) {
                    ticketDTOS.add(new TicketDTO(evt, occ, (EvCategoryFreeDTO) cat));
                }
            }
            PaymentInfo bookingInfo = body.getBookingInfo();
            Client client = new Client(bookingInfo.getNameL() + bookingInfo.getNameF() + System.currentTimeMillis(), bookingInfo.getNameL() + ", " + bookingInfo.getNameF(), Arrays.asList(clientRepo.getWebRole()), new HashSet<>(), bookingInfo.toAddressEntity());
            System.out.println(client + " => " + client.getUsername());
            clientRepo.addClient(client);
            List<Ticket> ticket2Book = entityRepo.toTickets(ticketDTOS, client);
            PaymentProcessor.PaymentTransaction paymentTransaction = paymentProcessor.buy(ticket2Book, bookingInfo);
            if (paymentTransaction != null) {
                log.info("Size of Tickets to book: {}", ticket2Book.size());
                List<Ticket> bookedTickets = injector.getBookingServiceCore().bookTickets(ticket2Book);
                List<BookingResponse> bookingResponses;
                if (bookedTickets.isEmpty()) {
                    paymentTransaction.abort();
                    return new ResponseEntity<>(Collections.singletonList(new BookingResponse().errMsg("Tickets could not be booked")), 409);
                } else if (isSeat) {
                    bookingResponses = ticket2Book.stream().map(t2b -> {
                        BookingResponse response = new BookingResponse();
                        Optional<Ticket> first = bookedTickets.stream().filter(t -> t.isSame(t2b)).findFirst();
                        if (first.isPresent()) {
                            response.tranactionId(first.get().getTicketId());
                        } else {
                            paymentTransaction.abort();
                            response.errMsg("Could not be booked INFO:" + t2b);
                        }
                        return response;
                    }).collect(Collectors.toList());
                } else {
                    if (bookedTickets.size() == ticket2Book.size()) {
                        Set<Long> used = new HashSet<>();
                        bookingResponses = ticket2Book.stream().map(t2b -> {
                            BookingResponse response = new BookingResponse();
                            Optional<Ticket> first = bookedTickets.stream().filter(t -> (t.isSame(t2b)) ? used.contains(t.getTicketId()) ? false : used.add(t.getTicketId()) : false).findFirst();
                            if (first.isPresent()) {
                                response.tranactionId(first.get().getTicketId());
                            } else {
                                paymentTransaction.abort();
                                response.errMsg("Could not be booked INFO:" + t2b);
                            }
                            return response;
                        }).collect(Collectors.toList());
                    } else {
                        paymentTransaction.abort();
                        throw new RuntimeException("Unexpected Error - No tickets should have been booked");
                    }
                }
                if (paymentTransaction.isValid()) {
                    boolean commit = paymentTransaction.commit();
                    if (commit) {
                        return new ResponseEntity<>(bookingResponses, 200);
                    } else {
                        //Out of scope - Free tickets again
                        return new ResponseEntity<>(Collections.singletonList(new BookingResponse().errMsg("Payment could not be processed")), 409);
                    }
                } else {
                    return new ResponseEntity<>(Collections.singletonList(new BookingResponse().errMsg("Payment could not be processed")), 409);
                }
            } else {
                return new ResponseEntity<>(Collections.singletonList(new BookingResponse().errMsg("No connection to payment processor possible")), 409);
            }
        } catch (NotFoundException | IllegalArgumentException e) {
            log.error("Did not found parts of input", e);
            return new ResponseEntity<>(409);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(500);
        }
    }

    class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }


    public ResponseEntity<List<EventDTO>> eventsFindByQueryStringGet(String queryString) {
        try {
            SearchServiceCore searchServiceCore = injector.getSearchServiceCore();
            EntityDTORepo entityRepo = injector.getEntityRepo();
            SearchService searchService = new SearchServiceREST(searchServiceCore, entityRepo);
            List<EventDTO> events = searchService.searchFor(queryString);
            if (events.isEmpty()) {
                return new ResponseEntity<>(204);
            } else {
                return new ResponseEntity<>(events, 200);
            }
        } catch (Exception e) {
            if (e instanceof SearchException) {
                log.error("Couldn't parse search string", e);
                return new ResponseEntity<>(400);
            }
            log.error("Couldn't serialize response for content type application/json", e);
            log.error("{}", e.getClass());
            return new ResponseEntity<>(500);
        }

    }

}

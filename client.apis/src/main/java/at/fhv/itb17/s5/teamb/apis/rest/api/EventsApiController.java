package at.fhv.itb17.s5.teamb.apis.rest.api;

import at.fhv.itb17.s5.teamb.apis.rest.model.BookingResponse;
import at.fhv.itb17.s5.teamb.apis.rest.model.RowSeat;
import at.fhv.itb17.s5.teamb.apis.rest.model.TicketOrder;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.controllers.rest.SearchServiceREST;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
@Controller
public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<BookingResponse>> bookTicket(
            @ApiParam(value = "ID of the event to book", required = true)
            @PathVariable("eventID") Long eventID,
            @ApiParam(value = "ID of the occurence to book", required = true)
            @PathVariable("occID") Long occID,
            @ApiParam(value = "ID of the category to book", required = true)
            @PathVariable("catID") Long catID,
            @ApiParam(value = "")
            @Valid @RequestBody TicketOrder body) {
        String accept = request.getHeader("Accept");
        log.info("Accept is json: {}", (accept != null && accept.contains("application/json")));
        System.out.println("eventID = [" + eventID + "], occID = [" + occID + "], catID = [" + catID + "], body = [" + body + "]");
        try {
            EntityDTORepo entityRepo = InjectHolder.injector.getEntityRepo();
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
            Client client = new Client(); //TODO GET CLIENT DATA
            List<Ticket> ticket2Book = entityRepo.toTickets(ticketDTOS, client);
            log.info("Size of Tickets to book: {}", ticket2Book.size());
            List<Ticket> bookedTickets = InjectHolder.injector.getBookingServiceCore().bookTickets(ticket2Book);
            if (bookedTickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (isSeat) {
                ticket2Book.stream().map(t2b -> {
                    BookingResponse response = new BookingResponse();
                    Optional<Ticket> first = bookedTickets.stream().filter(t -> t.isSame(t2b)).findFirst();
                    if (first.isPresent()) {
                        response.tranactionId(first.get().getTicketId());
                    } else {
                        response.errMsg("Could not be booked INFO:" + t2b);
                    }
                    return response;
                });
            } else {
                if (bookedTickets.size() == ticket2Book.size()) {
                    ticket2Book.stream().map(t2b -> {
                        BookingResponse response = new BookingResponse();
                        Optional<Ticket> first = bookedTickets.stream().filter(t -> t.isSame(t2b)).findFirst();
                        if (first.isPresent()) {
                            response.tranactionId(first.get().getTicketId());
                        } else {
                            response.errMsg("Could not be booked INFO:" + t2b);
                        }
                        return response;
                    });
                } else {
                    throw new RuntimeException("Unexpected Error - No tickets should have been booked");
                }
            }
            List<BookingResponse> bookingResponses = Arrays.asList(new BookingResponse().tranactionId(50L), new BookingResponse().tranactionId(51L));
            return new ResponseEntity<List<BookingResponse>>(bookingResponses, HttpStatus.OK);
        } catch (NotFoundException | IllegalArgumentException e) {
            log.error("Did not found parts of input", e);
            return new ResponseEntity<List<BookingResponse>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<BookingResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }


    public ResponseEntity<List<EventDTO>> eventsFindByQueryStringGet(@ApiParam(value = "QueryString used as a filter") @RequestParam(value = "queryString", required = false, defaultValue = "") String queryString) {
        String accept = request.getHeader("Accept");
        log.debug("Accept is json: {}", (accept != null && accept.contains("application/json")));
        try {
            SearchService searchService = new SearchServiceREST(InjectHolder.injector.getSearchServiceCore(), InjectHolder.injector.getEntityRepo());
            List<EventDTO> events = searchService.searchFor(queryString);
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<EventDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
package at.fhv.itb17.s5.teamb.apis.rest.api;

import at.fhv.itb17.s5.teamb.apis.rest.model.BookingResponse;
import at.fhv.itb17.s5.teamb.apis.rest.model.TicketOrder;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.EventMapper;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public ResponseEntity<List<BookingResponse>> bookTicket(@ApiParam(value = "ID of the event to book", required = true) @PathVariable("eventID") Long eventID, @ApiParam(value = "ID of the occurence to book", required = true) @PathVariable("occID") Long occID, @ApiParam(value = "ID of the category to book", required = true) @PathVariable("catID") Long catID, @ApiParam(value = "") @Valid @RequestBody TicketOrder body) {
        String accept = request.getHeader("Accept");
        log.debug("Accept is json: {}", (accept != null && accept.contains("application/json")));
        try {
            List<BookingResponse> bookingResponses = Arrays.asList(new BookingResponse().tranactionId(50L), new BookingResponse().tranactionId(51L));
            return new ResponseEntity<List<BookingResponse>>(bookingResponses, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<BookingResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<EventDTO>> eventsFindByQueryStringGet(@ApiParam(value = "QueryString used as a filter") @RequestParam(value = "queryString", required = false) String queryString) {
        String accept = request.getHeader("Accept");
        log.debug("Accept is json: {}", (accept != null && accept.contains("application/json")));
        try {
            List<EventDTO> events = getEvents();

            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.valueOf(204));
            } else {
                return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<EventDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public LinkedList<EventDTO> getEvents() {
        EventCategory evCat0 = new EventCategory("cat_name_ev0", 99 * 100, 5000, 4711);
        LinkedList<LocationRow> locationRows = new LinkedList<>(Arrays.asList(new LocationRow("A", Arrays.asList(new LocationSeat("1", true), new LocationSeat("2", false), new LocationSeat("3", false))), new LocationRow("B", Arrays.asList(new LocationSeat("45", true), new LocationSeat("46", true)))));
        EventCategory evCat1 = new EventCategory("cat_name_ev1", 20 * 100, locationRows);
        LinkedList<EventCategory> cats = new LinkedList<>(Arrays.asList(evCat0, evCat1));
        Address addressEvOc0 = new Address("evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        Address addressEvOc1 = new Address("evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        EventOccurrence evOccurrenceDTO0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), cats, addressEvOc0);
        EventOccurrence evOccurrenceDTO1 = new EventOccurrence(LocalDate.now().plusDays(3), LocalTime.now(), cats, addressEvOc1);
        LinkedList<EventOccurrence> occurrences = new LinkedList<>(Arrays.asList(evOccurrenceDTO0, evOccurrenceDTO1));
        LinkedList<Artist> artistNames = new LinkedList<>(Arrays.asList(new Artist("Hugo Hugo"), new Artist("Franz Peter Werner")));
        Address addressOrg0 = new Address("org0_country", "org0_zip", "org0_city", "org0_street", "org0_house");
        Address addressOrg1 = new Address("org1_country", "org1_zip", "org1_city", "org1_street", "org1_house");
        Organizer org0 = new Organizer("org0_name", "org0_email", addressOrg0);
        Organizer org1 = new Organizer("org1_name", "org1_email", addressOrg1);
        Event eventDTO0 = new Event("Demo Concert0", "A very descriptive description0", "08/15 0", occurrences, org0, artistNames);
        Event eventDTO1 = new Event("Demo Concert1", "A very descriptive description1", "08/15 1", occurrences, org1, artistNames);
        LinkedList<Event> events = new LinkedList<>(Arrays.asList(eventDTO0, eventDTO1));
        return new LinkedList<>(EventMapper.toDTOs(events));
    }

}

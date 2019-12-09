package at.fhv.itb17.s5.teamb.apis.rest.api;

import at.fhv.itb17.s5.teamb.apis.rest.model.BookingResponse;
import at.fhv.itb17.s5.teamb.apis.rest.model.Event;
import at.fhv.itb17.s5.teamb.apis.rest.model.TicketOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    public ResponseEntity<List<BookingResponse>> bookTicket(@ApiParam(value = "ID of the event to book",required=true) @PathVariable("eventID") Long eventID,@ApiParam(value = "ID of the occurence to book",required=true) @PathVariable("occID") Long occID,@ApiParam(value = "ID of the category to book",required=true) @PathVariable("catID") Long catID,@ApiParam(value = ""  )  @Valid @RequestBody TicketOrder body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<BookingResponse>>(objectMapper.readValue("[ {\n  \"tranactionId\" : 0,\n  \"errMsg\" : \"errMsg\"\n}, {\n  \"tranactionId\" : 0,\n  \"errMsg\" : \"errMsg\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<BookingResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<BookingResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Event> eventsFindByQueryStringGet(@ApiParam(value = "QueryString used as a filter") @Valid @RequestParam(value = "queryString", required = false) String queryString) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Event>(objectMapper.readValue("{\n  \"artistNames\" : [ \"artistNames\", \"artistNames\" ],\n  \"occurrences\" : [ {\n    \"date\" : 1,\n    \"zip\" : \"zip\",\n    \"country\" : \"country\",\n    \"priceCategories\" : [ {\n      \"priceInCents\" : 9,\n      \"totalTickets\" : 2,\n      \"usedTickets\" : 7,\n      \"rows\" : [ {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      }, {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      } ],\n      \"categoryName\" : \"categoryName\",\n      \"eventCategoryId\" : 5\n    }, {\n      \"priceInCents\" : 9,\n      \"totalTickets\" : 2,\n      \"usedTickets\" : 7,\n      \"rows\" : [ {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      }, {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      } ],\n      \"categoryName\" : \"categoryName\",\n      \"eventCategoryId\" : 5\n    } ],\n    \"categoryCalcDataDTO\" : {\n      \"minPrice\" : 7,\n      \"ticketTypes\" : \"ticketTypes\",\n      \"maxPrice\" : 1\n    },\n    \"city\" : \"city\",\n    \"street\" : \"street\",\n    \"occurrenceId\" : 6,\n    \"time\" : 5,\n    \"house\" : \"house\",\n    \"addressId\" : 4\n  }, {\n    \"date\" : 1,\n    \"zip\" : \"zip\",\n    \"country\" : \"country\",\n    \"priceCategories\" : [ {\n      \"priceInCents\" : 9,\n      \"totalTickets\" : 2,\n      \"usedTickets\" : 7,\n      \"rows\" : [ {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      }, {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      } ],\n      \"categoryName\" : \"categoryName\",\n      \"eventCategoryId\" : 5\n    }, {\n      \"priceInCents\" : 9,\n      \"totalTickets\" : 2,\n      \"usedTickets\" : 7,\n      \"rows\" : [ {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      }, {\n        \"rowIdf\" : \"rowIdf\",\n        \"seats\" : {\n          \"seatIdf\" : \"seatIdf\",\n          \"isFree\" : true,\n          \"seatId\" : 2\n        },\n        \"rowId\" : 3\n      } ],\n      \"categoryName\" : \"categoryName\",\n      \"eventCategoryId\" : 5\n    } ],\n    \"categoryCalcDataDTO\" : {\n      \"minPrice\" : 7,\n      \"ticketTypes\" : \"ticketTypes\",\n      \"maxPrice\" : 1\n    },\n    \"city\" : \"city\",\n    \"street\" : \"street\",\n    \"occurrenceId\" : 6,\n    \"time\" : 5,\n    \"house\" : \"house\",\n    \"addressId\" : 4\n  } ],\n  \"eventId\" : 0,\n  \"description\" : \"description\",\n  \"title\" : \"title\",\n  \"genere\" : \"genere\"\n}", Event.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Event>(HttpStatus.NOT_IMPLEMENTED);
    }

}

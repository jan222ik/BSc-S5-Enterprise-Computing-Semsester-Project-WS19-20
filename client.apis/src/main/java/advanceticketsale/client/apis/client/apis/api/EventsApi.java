package advanceticketsale.client.apis.client.apis.api;

import advanceticketsale.client.apis.client.apis.model.BookingResponse;
import advanceticketsale.client.apis.client.apis.model.TicketOrder;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
//@Api(value = "events", description = "the events API")
public interface EventsApi {

    //@ApiOperation(value = "Book tickets for a specific category of an event's occurrence", nickname = "bookTicket", notes = "Multiple status values can be provided with comma separated strings", response = BookingResponse.class, responseContainer = "List", tags = {"events",})
    //@ApiResponses(value = {
    //        @ApiResponse(code = 200, message = "Successful operation, but tickets may have not been booked, check payload errMsg for info", response = BookingResponse.class, responseContainer = "List"),
    //        @ApiResponse(code = 400, message = "Unpareable data and types"),
    //        @ApiResponse(code = 404, message = "Data in path or Body not linkable to entity"),
    //        @ApiResponse(code = 409, message = "If not enough tickets are avariable"),
    //        @ApiResponse(code = 500, message = "Unexpected server exception")})
    @RequestMapping(value = "/events/{eventID}/occurrences/{occID}/categories/{catID}/book",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<List<BookingResponse>> bookTicket(
            //@ApiParam(value = "ID of the event to book", required = true)
            @PathVariable("eventID") Long eventID,
            //@ApiParam(value = "ID of the occurence to book", required = true)
            @PathVariable("occID") Long occID,
            //@ApiParam(value = "ID of the category to book", required = true)
            @PathVariable("catID") Long catID,
            //@ApiParam(value = "")
            @Valid @RequestBody TicketOrder body);


    //@ApiOperation(value = "Query a list of events based on a search string", nickname = "eventsFindByQueryStringGet", notes = "", response = EventDTO.class, tags = {"events",})
    //@ApiResponses(value = {
    //        @ApiResponse(code = 200, message = "Request was successful, returns not empty result set found", response = EventDTO.class),
    //        @ApiResponse(code = 204, message = "Request was successful but resultset is empty"),
    //        @ApiResponse(code = 400, message = "Invalid QueryString"),
    //        @ApiResponse(code = 500, message = "Unexpected server exception")})
    @RequestMapping(value = "/events/findByQueryString",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<EventDTO>> eventsFindByQueryStringGet(
            //@ApiParam(value = "QueryString used as a filter")
            @Valid @RequestParam(value = "queryString", required = false) String queryString);

}
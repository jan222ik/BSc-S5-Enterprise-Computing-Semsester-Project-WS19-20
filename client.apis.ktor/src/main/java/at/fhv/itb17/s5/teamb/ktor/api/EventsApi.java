package at.fhv.itb17.s5.teamb.ktor.api;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.ktor.model.BookingResponse;
import at.fhv.itb17.s5.teamb.ktor.model.TicketOrder;

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
    ResponseEntity<List<BookingResponse>> bookTicket(Long eventID, Long occID, Long catID, TicketOrder body);


    //@ApiOperation(value = "Query a list of events based on a search string", nickname = "eventsFindByQueryStringGet", notes = "", response = EventDTO.class, tags = {"events",})
    //@ApiResponses(value = {
    //        @ApiResponse(code = 200, message = "Request was successful, returns not empty result set found", response = EventDTO.class),
    //        @ApiResponse(code = 204, message = "Request was successful but resultset is empty"),
    //        @ApiResponse(code = 400, message = "Invalid QueryString"),
    //        @ApiResponse(code = 500, message = "Unexpected server exception")})
    ResponseEntity<List<EventDTO>> eventsFindByQueryStringGet(String queryString);

}

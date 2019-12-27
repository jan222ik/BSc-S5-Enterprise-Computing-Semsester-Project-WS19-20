import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Tester {
    public static void main(String[] args) throws Exception {
        Properties jndiProps = new Properties();
//        jndiProps.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
//
//        jndiProps.put("remote.connections", "default");

//        clientProp.put("remote.connection.default.host", "localhost"); // comes from JVM argument
//
//        clientProp.put("remote.connection.default.port", "4447"); // comes from JVM argument
//
//        clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        jndiProps.put("java.naming.factory.initial",
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url",
                "http-remoting://localhost:8080");
        InitialContext initialContext = new InitialContext(jndiProps);
        SearchService stub = (SearchService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/SearchServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.SearchService");
        LinkedList<EventDTO> eventDTOS = stub.searchFor("");
        BookingService bookingService = (BookingService) initialContext.lookup("ejb:/core-beans-1.0-jar-with-dependencies/BookingServiceEJB!at.fhv.itb17.s5.teamb.core.controllers.general.BookingService");
        List<TicketDTO> tickets = new ArrayList<>();
        EventDTO event = eventDTOS.get(0);
        System.out.println(event.getOccurrences().get(0).getPriceCategories().get(0).getUsedTickets());
        tickets.add(new TicketDTO(event, event.getOccurrences().get(0), (EvCategoryFreeDTO) event.getOccurrences().get(0).getPriceCategories().get(0)));
        tickets.add(new TicketDTO(event, event.getOccurrences().get(0), (EvCategoryFreeDTO) event.getOccurrences().get(0).getPriceCategories().get(0)));
        tickets.add(new TicketDTO(event, event.getOccurrences().get(0), (EvCategoryFreeDTO) event.getOccurrences().get(0).getPriceCategories().get(0)));
        tickets.add(new TicketDTO(event, event.getOccurrences().get(0), (EvCategoryFreeDTO) event.getOccurrences().get(0).getPriceCategories().get(0)));
        bookingService.bookTickets(tickets);
    }
}
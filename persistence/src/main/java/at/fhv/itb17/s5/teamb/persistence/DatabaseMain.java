package at.fhv.itb17.s5.teamb.persistence;


import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRoles;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import at.fhv.itb17.s5.teamb.persistence.entities.TicketStates;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.EventRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories;
import at.fhv.itb17.s5.teamb.persistence.search.SearchPair;
import at.fhv.itb17.s5.teamb.persistence.util.WhereClauseBuilder;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("squid:S106")
public class DatabaseMain {

    private static EntityRepository repository = new EntityRepository();

    private static void test() {
        Address address = new Address("AT", "685ftui0", "Do", "ABCStr.", "4711");
        Client client = new Client("test_client", "Hugo Hugo", "pw", "salt", ClientRoles.EXTERNAL, address);
        EventCategory g21 = new EventCategory("G21", 9001, 500, 69);
        EventCategory g16 = new EventCategory("G16", 12312312, 19, 3);
        EventOccurrence occurrence0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address);
        Organizer organizer = new Organizer("Std. Do", "e@mail.com", address);
        Artist hugo_hugo = new Artist("Hugo Hugo");
        LinkedList<Artist> artists = new LinkedList<>(Arrays.asList(hugo_hugo));
        Event event = new Event("Weihnachtsmarkt", "Weihnachtsmarkt vom 22.11 bis 23.11.2019", "Death Metal", new LinkedList<>(Arrays.asList(occurrence0)), organizer, artists);
        Ticket ticket = new Ticket(client, TicketStates.PAID, event, occurrence0, g21, null, null);


        List<EventOccurrence> occurrences = new ArrayList<>();
        occurrences.add(new EventOccurrence(LocalDate.of(2020, 01, 01), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(2021, 12, 02), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(2022, 05, 12), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));

        List<Event> events = new ArrayList<>();
        occurrences = new ArrayList<>();
        occurrences.add(new EventOccurrence(LocalDate.of(1020, 01, 01), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(2021, 12, 02), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(3022, 05, 12), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));

        events.add(new Event("Sponsion", "Sponsion der FHV", "Veranstaltung", occurrences, organizer, null));
        occurrences = new ArrayList<>();
        occurrences.add(new EventOccurrence(LocalDate.of(2020, 01, 01), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(2021, 12, 02), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));
        occurrences.add(new EventOccurrence(LocalDate.of(2022, 05, 12), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address));

        events.add(new Event("Weinverkostung", "Verein der feinen Trinker", "Kulturbesäufnis", occurrences, organizer, new LinkedList<>(Arrays.asList(new Artist("Die Blechtrommel")))));

        //repository.saveOrUpdate(ticket);
        //System.out.println("ticket = " + ticket.getTicketId());



        repository.saveOrUpdate(event);
        events.forEach(e -> repository.saveOrUpdate(e));



//        repository.save(address);
//        repository.saveOrUpdate(client);
//        repository.saveOrUpdate(g21);
//        repository.saveOrUpdate(g16);
//        repository.saveOrUpdate(occurrence0);
//        repository.saveOrUpdate(organizer);
//        repository.saveOrUpdate(event);
//        repository.saveOrUpdate(ticket);

//        repository.save(new Address("austria", "6850", "dornbirn", "knie", "60"));
//        repository.save(new Address("austria", "6850", "dornbirn", "rohrbach", "121"));
//        repository.save(new Address("austria", "6840", "bregenz", "langestrasse", "12"));
//        repository.save(new Address("austria", "6850", "hörbranz", "nemetzstraße", "420"));
//        repository.save(new Address("deutschland", "61250", "landsberg", "jannikstreet", "0815"));

    /*
        EventRepository eventRepository = new EventRepository(new EntityRepository());
        List<SearchPair> searchPairs = new ArrayList<>();
        searchPairs.add(new SearchPair(SearchCategories.EVENT_NAME, "Spo"));
        List<Event> search = eventRepository.search(searchPairs);
        search.forEach(e -> System.out.println(e));
     */
        TicketRepository ticketRepository = new TicketRepository(repository);
        Ticket ticket1 = ticketRepository.bookIfFree(ticket);
        System.out.println("hello");
    }

    public static void main(String... test) {
        test();
    }


}

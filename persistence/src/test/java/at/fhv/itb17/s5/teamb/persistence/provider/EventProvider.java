package at.fhv.itb17.s5.teamb.persistence.provider;

import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EventProvider {

    public static Event getNewEventAndAddDB(String cName) {
        Address address = new Address(cName + "AT", cName + "685ftui0", cName + "Do", cName + "ABCStr.", cName + "4711");
        Client client = new Client(cName + "test_client", cName + "Hugo Hugo", new LinkedList<>(), new LinkedList<>(), address);
        EventCategory g21 = new EventCategory(cName + "G21", 9001, 500, 69);
        EventCategory g16 = new EventCategory(cName + "G16", 12312312, 19, 3);
        EventOccurrence occurrence0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address);
        Organizer organizer = new Organizer(cName + "Std. Do", cName + "e@mail.com", address);
        Artist hugo_hugo = new Artist(cName + "Hugo Hugo");
        List<Artist> artists = new LinkedList<>(Collections.singletonList(hugo_hugo));
        return new Event(cName + "Weihnachtsmarkt", cName + "Weihnachtsmarkt vom 22.11 bis 23.11.2019", cName + "Death Metal", new LinkedList<>(Collections.singletonList(occurrence0)), organizer, artists);
    }

    public static Event getNewTransientEvent(String cName) {
        Address address = new Address(cName + "AT", cName + "685ftui0", cName + "Do", cName + "ABCStr.", cName + "4711");
        Client client = new Client(cName + "test_client", cName + "Hugo Hugo", new LinkedList<>(), new LinkedList<>(), address);
        EventCategory g21 = new EventCategory(cName + "G21", 9001, 500, 69);
        EventCategory g16 = new EventCategory(cName + "G16", 12312312, 19, 3);
        EventOccurrence occurrence0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), new LinkedList<>(Arrays.asList(g21, g16)), address);
        Organizer organizer = new Organizer(cName + "Std. Do", cName + "e@mail.com", address);
        Artist hugo_hugo = new Artist(cName + "Hugo Hugo");
        List<Artist> artists = new LinkedList<>(Collections.singletonList(hugo_hugo));
        return new Event(cName + "Weihnachtsmarkt", cName + "Weihnachtsmarkt vom 22.11 bis 23.11.2019", cName + "Death Metal", new LinkedList<>(Collections.singletonList(occurrence0)), organizer, artists);
    }


}

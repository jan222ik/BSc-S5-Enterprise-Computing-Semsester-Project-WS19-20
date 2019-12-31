package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepoImpl;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCoreImpl;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCoreImpl;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCoreImpl;
import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Artist;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.entities.Organizer;
import at.fhv.itb17.s5.teamb.persistence.repository.ClientRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.EventRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"squid:S1192", "squid:CommentedOutCodeLine", "FieldCanBeLocal"})
public class CoreServiceInjectorImpl implements CoreServiceInjector {

    private static CoreServiceInjector injector;

    private final EntityRepository entityRepository = new EntityRepository();
    private final EventRepository eventRepository = new EventRepository(entityRepository);
    private final SearchServiceCore searchServiceCore = new SearchServiceCoreImpl(eventRepository);
    private final TicketRepository ticketRepository = new TicketRepository(entityRepository);
    private final BookingServiceCore bookingServiceCore = new BookingServiceCoreImpl(ticketRepository);
    private final ClientRepository clientRepository = new ClientRepository(entityRepository);

    private final AuthManagerCore authManagerCore;
    private final EntityDTORepo entityDTORepo = new EntityDTORepoImpl();
    private final MsgRepository msgRepository = new MsgRepository(entityRepository);
    private final MsgServiceCore msgTopicServiceCore;

    private CoreServiceInjectorImpl(boolean withLDAP) {
        injector = this;
        authManagerCore = new AuthManagerCore(true, withLDAP, clientRepository);
        addDBDATA();
        msgTopicServiceCore = new MsgServiceCoreImpl(msgRepository);
    }

    public static CoreServiceInjector getInstance(boolean withLDAP){
        if(injector == null){
            injector = new CoreServiceInjectorImpl(withLDAP);
        }
        return injector;
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private void addDBDATA() {
        /*
        EventCategory evCat0 = new EventCategory("cat_name_ev0", 99 * 100, 5000, 4711);
        EventCategory evCat1 = new EventCategory("cat_name_ev1", 20 * 100, 800, 11);
        LinkedList<EventCategory> cats = new LinkedList<>(Arrays.asList(evCat0, evCat1));
        Address addressEvOc0 = new Address("evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        Address addressEvOc1 = new Address("evt_country", "evt_zip", "evt_city", "evt_street", "evt_house");
        EventOccurrence evOccurrenceDTO0 = new EventOccurrence(LocalDate.now(), LocalTime.now(), cats, addressEvOc0);
        EventOccurrence evOccurrenceDTO1 = new EventOccurrence(LocalDate.now().plusDays(3), LocalTime.now(), cats, addressEvOc1);
        EventOccurrence evOccurrenceDTO2 = new EventOccurrence(LocalDate.now(), LocalTime.now(), cats, addressEvOc0);
        EventOccurrence evOccurrenceDTO3 = new EventOccurrence(LocalDate.now().plusDays(3), LocalTime.now(), cats, addressEvOc1);
        LinkedList<EventOccurrence> occurrences = new LinkedList<>(Arrays.asList(evOccurrenceDTO0, evOccurrenceDTO1));
        LinkedList<EventOccurrence> occurrences1 = new LinkedList<>(Arrays.asList(evOccurrenceDTO2, evOccurrenceDTO3));
        LinkedList<Artist> artistNames = new LinkedList<>(Arrays.asList(new Artist("Hugo Hugo"), new Artist("Franz Peter Werner")));
        Address addressOrg0 = new Address("org0_country", "org0_zip", "org0_city", "org0_street", "org0_house");
        Address addressOrg1 = new Address("org1_country", "org1_zip", "org1_city", "org1_street", "org1_house");
        Organizer org0 = new Organizer("org0_name", "org0_email", addressOrg0);
        Organizer org1 = new Organizer("org1_name", "org1_email", addressOrg1);
        Event eventDTO0 = new Event("Demo Concert0", "A very descriptive description0", "08/15 0", occurrences, org0, artistNames);
        Event eventDTO1 = new Event("Demo Concert1", "A very descriptive description1", "08/15 1", occurrences1, org1, artistNames);
        entityRepository.saveOrUpdate(eventDTO0);
        entityRepository.saveOrUpdate(eventDTO1);

         */

        List<EventOccurrence> sceneOccurrences = new ArrayList<>();
        sceneOccurrences.add(
                new EventOccurrence(LocalDate.of(2020, 6, 12),
                        LocalTime.of(12, 30, 0),
                        Arrays.asList(
                                new EventCategory("VIP", 5000, 30, 0),
                                new EventCategory("Standard", 2000, 200, 0)
                        ),
                        new Address("Österreich", "6830", "Lustenau", "Hohegasse", "12b")
                ));
        sceneOccurrences.add(
                new EventOccurrence(LocalDate.of(2021, 6, 10),
                        LocalTime.of(13, 0, 0),
                        Arrays.asList(
                                new EventCategory("VIP", 5500, 40, 0),
                                new EventCategory("Standard", 2500, 250, 0)
                        ),
                        new Address("Österreich", "6830", "Lustenau", "Hohegasse", "12b")
                ));
        sceneOccurrences.add(
                new EventOccurrence(LocalDate.of(2020, 6, 15),
                        LocalTime.of(12, 0, 0),
                        Arrays.asList(
                                new EventCategory("VIP", 6000, 50, 0),
                                new EventCategory("Standard", 3000, 400, 0)
                        ),
                        new Address("Österreich", "6830", "Lustenau", "Hohegasse", "12b")
                ));

        List<Artist> sceneArtists = new ArrayList<>();
        sceneArtists.add(new Artist("Capital Bro"));
        sceneArtists.add(new Artist("Imagine Unicorns"));

        List<EventOccurrence> sceneWritingOccurence = new ArrayList<>();
        sceneWritingOccurence.add(
                new EventOccurrence(
                        LocalDate.of(2019, 8, 14),
                        LocalTime.of(16, 30, 0),
                        Arrays.asList(
                                new EventCategory("Standardkarte", 5000, 50, 0)
                        ), new Address("Österreich", "6850", "Dornbirn", "Marktstraße", "19")));
        sceneWritingOccurence.add(
                new EventOccurrence(
                        LocalDate.of(2019, 8, 20),
                        LocalTime.of(16, 0, 0),
                        Arrays.asList(
                                new EventCategory("Standardkarte", 5000, 50, 0)
                        ), new Address("Österreich", "6850", "Dornbirn", "Marktstraße", "19")
                ));

        List<EventOccurrence> sponsionOccurences = new ArrayList<>();
        sponsionOccurences.add(
                new EventOccurrence(
                        LocalDate.of(2019, 10, 20),
                        LocalTime.of(10, 0, 0),
                        Arrays.asList(
                                new EventCategory("Gratis", 0, 350, 0)
                        ), new Address("Östrreich", "6810", "Bregenz", "Seestraße", "12")
                ));

        List<EventOccurrence> klaasOccurence = new ArrayList<>();
        klaasOccurence.add(new EventOccurrence(LocalDate.of(2020, 01, 15), LocalTime.of(18, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 1500, 100, 0)), new Address("Deutschland", "10115", "Berlin", "Kreuzbergstraße", "120")));
        klaasOccurence.add(new EventOccurrence(LocalDate.of(2019, 01, 22), LocalTime.of(18, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 1500, 100, 10)), new Address("Deutschland", "10115", "Berlin", "Kreuzbergstraße", "120")));
        klaasOccurence.add(new EventOccurrence(LocalDate.of(2019, 01, 29), LocalTime.of(18, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 1500, 100, 11)), new Address("Deutschland", "10115", "Berlin", "Kreuzbergstraße", "120")));

        List<EventOccurrence> kochshowOccurence = new ArrayList<>();
        List<LocationRow> seatingRows = new ArrayList<>();
        seatingRows.add(new LocationRow("Row 1", Arrays.asList(new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false))));
        seatingRows.add(new LocationRow("Row 2", Arrays.asList(new LocationSeat("Seat 6", false),
                new LocationSeat("Seat 7", false),
                new LocationSeat("Seat 8", false),
                new LocationSeat("Seat 9", false),
                new LocationSeat("Seat 10", true))));
        kochshowOccurence.add(new EventOccurrence(LocalDate.of(2019, 12, 20), LocalTime.of(16, 30, 0), Arrays.asList(new EventCategory("Standardeintritt", 500, 80, 0)), new Address("Deutschland", "10115", "Berlin", "Langestraße", "44")));
        kochshowOccurence.add(new EventOccurrence(LocalDate.of(2019, 12, 25), LocalTime.of(18, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 500, 80, 0)), new Address("Deutschland", "10115", "Berlin", "Langestraße", "44")));
        kochshowOccurence.add(new EventOccurrence(LocalDate.of(2019, 12, 30), LocalTime.of(18, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 600, seatingRows)), new Address("Deutschland", "10115", "Berlin", "Langestraße", "44")));

        List<EventOccurrence> karaokeOccurrence = new ArrayList<>();
        List<LocationRow> karaokeSeatingRows = new ArrayList<>();
        karaokeSeatingRows.add(new LocationRow("Row 1", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        karaokeSeatingRows.add(new LocationRow("Row 2", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        karaokeSeatingRows.add(new LocationRow("Row 3", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        List<LocationRow> karaokeSeatingRows2 = new ArrayList<>();
        karaokeSeatingRows2.add(new LocationRow("Row 1", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        karaokeSeatingRows2.add(new LocationRow("Row 2", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        karaokeSeatingRows2.add(new LocationRow("Row 3", Arrays.asList(
                new LocationSeat("Seat 1", false),
                new LocationSeat("Seat 2", false),
                new LocationSeat("Seat 3", false),
                new LocationSeat("Seat 4", false),
                new LocationSeat("Seat 5", false),
                new LocationSeat("Seat 6", false))));

        karaokeOccurrence.add(new EventOccurrence(LocalDate.of(2020, 10, 20), LocalTime.of(22, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 1000, karaokeSeatingRows)), new Address("Österreich", "6900", "Bregenz", "Arlbergstraße", "100")));
        karaokeOccurrence.add(new EventOccurrence(LocalDate.of(2020, 10, 21), LocalTime.of(22, 0, 0), Arrays.asList(new EventCategory("Standardeintritt", 1000, karaokeSeatingRows2)), new Address("Österreich", "6900", "Bregenz", "Arlbergstraße", "100")));

        List<Event> events = new LinkedList<>();
        events.add(new Event("Scene-Openair Lustenau", "Openair Festival in Lustenau", "Festival", sceneOccurrences, new Organizer("Lustenau Festivalverband", "scene@lustenau.at", new Address("Österreich", "6830", "Lustenau", "Langegasse", "23")), sceneArtists));
        events.add(new Event("Scene-Writing", "Scene Writing für Amateure", "Vortrag", sceneWritingOccurence, new Organizer("Amateurtheater Hohenems", "info@hohenems-play.at", new Address("Österreich", "6870", "Hohenems", "Bergreuthe", "5")), Arrays.asList(new Artist("Markus Riedmann"))));
        events.add(new Event("Sponsion der FHV", "Abschlussfeier für Bachelor und Master", "Kulturveranstaltung", sponsionOccurences, new Organizer("FH Vorarlberg", "info@fhv.at", new Address("Österreich", "6850", "Dornbirn", "Hochschulstraße", "1")), Arrays.asList(new Artist("2nd Dimension"))));
        events.add(new Event("Latenight Berlin", "Klaas hat Spaas", "Unterhaltung", klaasOccurence, new Organizer("Pro 7", "redaktion@prosieben.de", new Address("Deutschland", "10115", "Berlin", "Kreuzbergstraße", "120")), Arrays.asList(new Artist("Klaas Heufer-Umlauf"))));
        events.add(new Event("Kochshow", "Kochshow mit Janik Mayr", "Unterhaltung", kochshowOccurence, new Organizer("Satteins", "janik.mayr@satteins.de", new Address("Deutschland", "10115", "Berlin", "Kölschwasser", "4711")), Arrays.asList(new Artist("Dr. Janik Mayr"))));
        events.add(new Event("Karaoke Night", "Karaokespaß für jung und alt", "Unterhaltung", karaokeOccurrence, new Organizer("Karaoke GmbH", "karaoke-group@bregenz.at", new Address("Österreich", "6900", "Bregenz", "Seestraße", "55")), Arrays.asList(new Artist("Karaoke Guru"))));

        events.forEach(entityRepository::saveOrUpdate);


        List<MsgTopic> topics = new LinkedList<>();
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);
        MsgTopic theater = new MsgTopic("THEATER", false);
        MsgTopic test = new MsgTopic("TEST", false); //only to be used for testing!
        entityRepository.saveOrUpdate(test);
        topics.add(system);
        topics.add(rock);
        topics.add(opera);
        topics.add(theater);
        topics.forEach(entityRepository::saveOrUpdate);

        ClientRole admin = new ClientRole("ADMIN", true, true, 10);
        ClientRole web = new ClientRole("WEB", false, false, 0);
        ClientRole nobody = new ClientRole("NOBODY", false, false, 0);
        ClientRole onlyRead = new ClientRole("READER", true, false, 5);
        ClientRole messager = new ClientRole("MESSAGER", true, true, 7);
        entityRepository.saveOrUpdate(admin);
        entityRepository.saveOrUpdate(web);
        entityRepository.saveOrUpdate(messager);
        entityRepository.saveOrUpdate(nobody);
        entityRepository.saveOrUpdate(onlyRead);
        entityRepository.saveOrUpdate(new Client("backdoor", "Door, Back", Arrays.asList(admin), new HashSet<>(topics), new Address("Country", "zip", "city", "street", "house")));
        entityRepository.saveOrUpdate(new Client("tf-test2", "Testor2", Arrays.asList(onlyRead), new HashSet<>(Arrays.asList(system)), new Address("AT", "9999", "SimCity", "Hudson Steet", "13")));
        entityRepository.saveOrUpdate(new Client("FHVUser", "User, FHV", Arrays.asList(messager), new HashSet<>(Arrays.asList(system, rock, opera)), new Address("D", "0000", "Münchshausen", "Baumgasse", "12")));

    }

    @Override
    public SearchServiceCore getSearchServiceCore() {
        return searchServiceCore;
    }

    @Override
    public BookingServiceCore getBookingServiceCore() {
        return bookingServiceCore;
    }

    @Override
    public AuthManagerCore getAuthManagerCore() {
        return authManagerCore;
    }

    @Override
    public EntityDTORepo getEntityRepo() {
        return entityDTORepo;
    }

    @Override
    public MsgServiceCore getMsgTopicServiceCore() {
        return msgTopicServiceCore;
    }

    @Override
    public EntityRepository getEntityRepository() {
        return entityRepository;
    }

    @Override
    public ClientRepository getClientRepo() {
        return clientRepository;
    }
}

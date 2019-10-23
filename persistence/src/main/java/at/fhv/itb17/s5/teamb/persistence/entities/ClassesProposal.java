package at.fhv.itb17.s5.teamb.persistence.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused", "InnerClassMayBeStatic", "squid:S2094", "squid:S1068"})
public class ClassesProposal {

    @Entity
    public class Event {
        @Id
        private long eventId;
        private String title;
        private String description;
        private String genre;
        private String ageRuling;
        private List<EventOccurrence> occurrences;
        private Organizer organizer;
        private Address address;
    }

    public class EventOccurrence {
        @Id private long occurrenceId;
        private LocalDate date;
        private LocalTime time;
        private List<EventCategory> priceCategories;
    }

    public class EventCategory {
        @Id private long eventCategoryId;
        private boolean isFreeSeating;
        private String categoryName;
        private int priceInCent;
        private List<LocationRow> seatingRows;
        private int totalSpace;
        private int usedSpace;
    }


    public class LocationRow {
        @Id
        private long rowId;
        private String rowIdentifier;
        private List<LocationSeat> seats;
    }

    public class LocationSeat {
        @Id
        private long seatId;
        private String seatIdentifier;
    }


    @Entity
    public class Ticket {
        @Id
        private long ticketId;
        private Client client;
        private TicketStates state;
        private Event bookedEvent;
        private EventOccurrence bookedOccurrence;
        private EventCategory bookedCategory;
        private LocationRow bookedRow;
        private LocationSeat bookedSeat;
    }


    public enum TicketStates {
        FREE, RESERVED, PAID
    }


    @Entity
    public class Organizer {
        @Id
        private long organizerId;
        private String name;
        private String email;
        private Address address;
    }

    @Entity
    public class Address {
        @Id
        private long addressId;
        private String country;
        private String zip;
        private String city;
        private String street;
        private String house;
    }

    public class Client {
        @Id
        private String username;
        private String name;
        private String password;
        private String salt;
        private ClientRoles role;
    }

    public enum ClientRoles {
        ADMIN, SALES_AGENT, EXTERNAL
    }
}

package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;
    private String title;
    private String description;
    private String genre;
    private String ageRuling;
    @OneToMany
    private List<EventOccurrence> occurrences;
    @ManyToOne
    private Organizer organizer;
    @OneToOne
    private Address address;

    public Long getEventId() {
        return eventId;
    }

    public Event(String title, String description, String genre, String ageRuling, List<EventOccurrence> occurrences, Organizer organizer, Address address) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.ageRuling = ageRuling;
        this.occurrences = occurrences;
        this.organizer = organizer;
        this.address = address;
    }

    public Event() {
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeRuling() {
        return ageRuling;
    }

    public void setAgeRuling(String ageRuling) {
        this.ageRuling = ageRuling;
    }

    public List<EventOccurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<EventOccurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

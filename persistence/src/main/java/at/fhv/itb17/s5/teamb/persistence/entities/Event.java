package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;
    private String title;
    private String description;
    private String genre;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<EventOccurrence> occurrences;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Organizer organizer;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Artist> artists;

    public Event(String title, String description, String genre, List<EventOccurrence> occurrences, Organizer organizer, List<Artist> artists) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.occurrences = occurrences;
        this.organizer = organizer;
        this.artists = artists;
    }

    public Event() {
    }

    public Long getEventId() {
        return eventId;
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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}

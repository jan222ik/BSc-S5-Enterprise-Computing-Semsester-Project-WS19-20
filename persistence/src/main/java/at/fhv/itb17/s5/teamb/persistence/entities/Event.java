package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.*;
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

    public Event(String title, String description, String genre, List<EventOccurrence> occurrences, Organizer organizer) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.occurrences = occurrences;
        this.organizer = organizer;
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
}

package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class EventOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long occurrenceId;
    private LocalDate date;
    private LocalTime time;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<EventCategory> priceCategories;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    public EventOccurrence(LocalDate date, LocalTime time, List<EventCategory> priceCategories, Address address) {
        this.date = date;
        this.time = time;
        this.priceCategories = priceCategories;
        this.address = address;
    }

    public EventOccurrence() {
    }

    public Long getOccurrenceId() {
        return occurrenceId;
    }

    public void setOccurrenceId(Long occurrenceId) {
        this.occurrenceId = occurrenceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<EventCategory> getPriceCategories() {
        return priceCategories;
    }

    public void setPriceCategories(List<EventCategory> priceCategories) {
        this.priceCategories = priceCategories;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

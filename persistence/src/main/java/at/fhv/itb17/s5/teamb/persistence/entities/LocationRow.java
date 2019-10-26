package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class LocationRow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long rowId;
    private String rowIdentifier;
    @OneToMany
    private List<LocationSeat> seats;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getRowIdentifier() {
        return rowIdentifier;
    }

    public void setRowIdentifier(String rowIdentifier) {
        this.rowIdentifier = rowIdentifier;
    }

    public List<LocationSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<LocationSeat> seats) {
        this.seats = seats;
    }
}

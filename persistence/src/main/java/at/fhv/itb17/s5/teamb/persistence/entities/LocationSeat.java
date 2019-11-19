package at.fhv.itb17.s5.teamb.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocationSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seatId;
    private String seatIdentifier;
    private boolean isTaken;

    public LocationSeat(String seatIdentifier, boolean isTaken) {
        this.seatIdentifier = seatIdentifier;
        this.isTaken = isTaken;
    }

    public LocationSeat() {
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatIdentifier() {
        return seatIdentifier;
    }

    public void setSeatIdentifier(String seatIdentifier) {
        this.seatIdentifier = seatIdentifier;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}

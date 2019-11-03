package at.fhv.itb17.s5.teamb.dtos;

import java.io.Serializable;

public class LocationSeatDTO implements Serializable {
    private Long seatId;
    private String seatIdentifier;

    public LocationSeatDTO(Long seatId, String seatIdentifier) {
        this.seatId = seatId;
        this.seatIdentifier = seatIdentifier;
    }

    public Long getSeatId() {
        return seatId;
    }

    public String getSeatIdentifier() {
        return seatIdentifier;
    }
}

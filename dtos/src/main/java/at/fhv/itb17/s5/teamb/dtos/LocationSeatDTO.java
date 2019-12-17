package at.fhv.itb17.s5.teamb.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LocationSeatDTO implements Serializable {
    @JsonProperty("seatId")
    private Long seatId;
    @JsonProperty("isFree")
    private boolean isFree;
    @JsonProperty("seatIdf")
    private String seatIdentifier;


    public LocationSeatDTO(Long seatId, boolean isFree, String seatIdentifier) {
        this.seatId = seatId;
        this.isFree = isFree;
        this.seatIdentifier = seatIdentifier;
    }

    public boolean isFree() {
        return isFree;
    }

    public Long getSeatId() {
        return seatId;
    }

    public String getSeatIdentifier() {
        return seatIdentifier;
    }
}

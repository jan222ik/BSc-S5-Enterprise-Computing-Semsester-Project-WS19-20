package at.fhv.itb17.s5.teamb.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class LocationRowDTO implements Serializable {
    @JsonProperty("rowId")
    private Long rowId;
    @JsonProperty("rowIdf")
    private String rowIdentifier;
    @JsonProperty("seats")
    private List<LocationSeatDTO> seats;

    public LocationRowDTO(Long rowId, String rowIdentifier, List<LocationSeatDTO> seats) {
        this.rowId = rowId;
        this.rowIdentifier = rowIdentifier;
        this.seats = seats;
    }

    public Long getRowId() {
        return rowId;
    }

    public String getRowIdentifier() {
        return rowIdentifier;
    }

    public List<LocationSeatDTO> getSeats() {
        return seats;
    }
}

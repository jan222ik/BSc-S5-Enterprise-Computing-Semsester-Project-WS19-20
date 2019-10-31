package at.fhv.itb17.s5.teamb.dtos;

public class LocationSeatDTO {
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

package at.fhv.itb17.s5.teamb.core.domain.booking;

import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;

public class CatRowSeatWrapper {
    private EventCategory cat;
    private LocationRow row;
    private LocationSeat seat;

    public CatRowSeatWrapper(EventCategory cat, LocationRow row, LocationSeat seat) {
        this.cat = cat;
        this.row = row;
        this.seat = seat;
    }

    public EventCategory getCat() {
        return cat;
    }

    public LocationRow getRow() {
        return row;
    }

    public LocationSeat getSeat() {
        return seat;
    }
}

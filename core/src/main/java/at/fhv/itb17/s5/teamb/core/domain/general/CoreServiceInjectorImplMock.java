package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCoreImplMock;

public class CoreServiceInjectorImplMock implements CoreServiceInjector {

    private final SearchServiceCore searchServiceCore = new SearchServiceCoreImplMock();
    private final BookingServiceCore bookingServiceCore = null;

    public SearchServiceCore getSearchServiceCore() {
        return searchServiceCore;
    }

    @Override
    public BookingServiceCore getBookingServiceCore() {
        return bookingServiceCore;
    }
}

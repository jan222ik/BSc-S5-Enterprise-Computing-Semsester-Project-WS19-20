package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;

public interface CoreServiceInjector {

    SearchServiceCore getSearchServiceCore();

    BookingServiceCore getBookingServiceCore();

    AuthManagerCore getAuthManagerCore();

    EntityDTORepo getEntityRepo();
}

package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepoImpl;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCoreImpl;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCoreImplMock;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;

public class CoreServiceInjectorImplMock implements CoreServiceInjector {

    private final SearchServiceCore searchServiceCore = new SearchServiceCoreImplMock();
    private final EntityRepository entityRepository = new EntityRepository();
    private final TicketRepository ticketRepository = new TicketRepository(entityRepository);
    private final BookingServiceCore bookingServiceCore = new BookingServiceCoreImpl(ticketRepository);
    private final AuthManagerCore authManagerCore = new AuthManagerCore(true); //TODO Use MockImpl
    private final EntityDTORepo dtoManager = new EntityDTORepoImpl();

    public SearchServiceCore getSearchServiceCore() {
        return searchServiceCore;
    }

    @Override
    public BookingServiceCore getBookingServiceCore() {
        return bookingServiceCore;
    }

    @Override
    public AuthManagerCore getAuthManagerCore() {
        return authManagerCore;
    }

    @Override
    public EntityDTORepo getEntityRepo() {
        return dtoManager;
    }
}

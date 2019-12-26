package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepo;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntityDTORepoImpl;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.booking.BookingServiceCoreImpl;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCoreImpl;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCore;
import at.fhv.itb17.s5.teamb.core.domain.search.SearchServiceCoreImplMock;
import at.fhv.itb17.s5.teamb.persistence.repository.ClientRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.EntityRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;
import at.fhv.itb17.s5.teamb.persistence.repository.TicketRepository;

public class CoreServiceInjectorImplMock implements CoreServiceInjector {

    private final SearchServiceCore searchServiceCore = new SearchServiceCoreImplMock();
    private final EntityRepository entityRepository = new EntityRepository();
    private final TicketRepository ticketRepository = new TicketRepository(entityRepository);
    private final BookingServiceCore bookingServiceCore = new BookingServiceCoreImpl(ticketRepository);
    private final ClientRepository clientRepository = new ClientRepository(entityRepository);
    private final AuthManagerCore authManagerCore = new AuthManagerCore(true, false, clientRepository); //TODO Use MockImpl
    private final EntityDTORepo dtoManager = new EntityDTORepoImpl();
    private final MsgRepository msgRepository = new MsgRepository(entityRepository);//TODO Use MockImpl
    private final MsgServiceCore msgTopicServiceCore = new MsgServiceCoreImpl(msgRepository);//TODO Use MockImpl

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

    @Override
    public MsgServiceCore getMsgTopicServiceCore() {
        return msgTopicServiceCore;
    }

    @Override
    public ClientRepository getClientRepo() {
        return null;
    }
}

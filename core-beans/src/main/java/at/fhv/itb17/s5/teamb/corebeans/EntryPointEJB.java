package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

public class EntryPointEJB extends EntryPoint {

    private static final Logger logger = LogManager.getLogger(EntryPointEJB.class);

    private ConnectionFactoryEJB factoryEJB;

    public EntryPointEJB(CoreServiceInjector coreImpl) throws RemoteException {
        super(coreImpl);
        factoryEJB = new ConnectionFactoryEJB(()
                -> new SearchServiceEJB(coreImpl.getSearchServiceCore(), coreImpl.getEntityRepo()), (ClientSessionRMI client)
                -> new BookingServiceEJB(coreImpl.getBookingServiceCore(), client, coreImpl.getEntityRepo()), (ClientSessionRMI client)
                -> new MsgTopicServiceEJB(coreImpl.getMsgTopicServiceCore(), client, coreImpl.getEntityRepo()), coreImpl.getAuthManagerCore());
    }
    @Override
    public void start() {
        logger.info("Started EJB");
    }

    @Override
    public void destroy() {

    }
}

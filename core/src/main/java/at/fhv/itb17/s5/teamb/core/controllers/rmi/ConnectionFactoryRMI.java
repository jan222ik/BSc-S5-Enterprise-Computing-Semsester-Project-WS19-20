package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.MsgTopicService;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.general.AuthManagerCore;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"squid:S2160", "squid:S1948", "RedundantThrows"})
public class ConnectionFactoryRMI extends UnicastRemoteObject implements IConnectionFactoryRMI {

    private static final Logger logger = LogManager.getLogger(ConnectionFactoryRMI.class);
    private Supplier<SearchService> searchServiceInstanceSupplier;
    private Function<ClientSessionRMI, MsgTopicService> topicServiceInstanceSupplier;
    private Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier;
    private AuthManagerCore authManagerCore;

    public ConnectionFactoryRMI(Supplier<SearchService> searchServiceInstanceSupplier, Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier, Function<ClientSessionRMI, MsgTopicService> topicServiceInstanceSupplier, AuthManagerCore authManagerCore) throws RemoteException {
        this.searchServiceInstanceSupplier = searchServiceInstanceSupplier;
        this.bookingServiceInstanceSupplier = bookingServiceInstanceSupplier;
        this.topicServiceInstanceSupplier = topicServiceInstanceSupplier;
        this.authManagerCore = authManagerCore;
    }

    @Override
    public SearchService createSearchService() {
        return searchServiceInstanceSupplier.get();
    }

    @Override
    public BookingService createBookingService(IFrontEndClient feClient, String username, String password) throws RemoteException {
        logger.info("Created BookingService for Client with the username: {}", username);
        if (authManagerCore.check(username, password)) {
            Client client = authManagerCore.queryClient(username);
            return bookingServiceInstanceSupplier.apply(new ClientSessionRMI(username, password, feClient, client));
        } else {
            return null;
        }
    }

    @Override
    public MsgTopicService createTopicService(String username, String password) throws RemoteException {
        logger.info("Created MsgService for Client with the username: {}", username);
        if (authManagerCore.check(username, password)) {
            Client client = authManagerCore.queryClient(username);
            return topicServiceInstanceSupplier.apply(new ClientSessionRMI(username, password, null, client));
        } else {
            return null;
        }
    }
}

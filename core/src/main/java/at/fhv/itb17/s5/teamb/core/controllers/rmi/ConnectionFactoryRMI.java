package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.IFrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.general.AuthManagerCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConnectionFactoryRMI extends UnicastRemoteObject implements IConnectionFactoryRMI {

    private static final Logger logger = LogManager.getLogger(ConnectionFactoryRMI.class);
    private Supplier<SearchService> searchServiceInstanceSupplier;
    private Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier;
    private AuthManagerCore authManagerCore;

    public ConnectionFactoryRMI(Supplier<SearchService> searchServiceInstanceSupplier, Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier, AuthManagerCore authManagerCore) throws RemoteException {
        this.searchServiceInstanceSupplier = searchServiceInstanceSupplier;
        this.bookingServiceInstanceSupplier = bookingServiceInstanceSupplier;
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
            return bookingServiceInstanceSupplier.apply(new ClientSessionRMI(username, password, feClient));
        } else {
            return null;
        }
    }
}

package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.BookingService;
import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.FrontEndClient;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConnectionFactoryRMI extends UnicastRemoteObject implements IConnectionFactoryRMI {

    private Supplier<SearchService> searchServiceInstanceSupplier;
    private Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier;

    public ConnectionFactoryRMI(Supplier<SearchService> searchServiceInstanceSupplier, Function<ClientSessionRMI, BookingService> bookingServiceInstanceSupplier) throws RemoteException {
        this.searchServiceInstanceSupplier = searchServiceInstanceSupplier;
        this.bookingServiceInstanceSupplier = bookingServiceInstanceSupplier;
    }

    @Override
    public SearchService createSearchService() {
        return searchServiceInstanceSupplier.get();
    }

    @Override
    public BookingService createBookingService(FrontEndClient feClient, String username, String password) throws RemoteException {
        return bookingServiceInstanceSupplier.apply(new ClientSessionRMI(username, password, feClient));
    }
}

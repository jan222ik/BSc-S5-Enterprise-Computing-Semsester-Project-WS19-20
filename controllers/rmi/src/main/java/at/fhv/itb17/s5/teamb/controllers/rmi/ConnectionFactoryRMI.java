package at.fhv.itb17.s5.teamb.controllers.rmi;

import at.fhv.itb17.s5.teamb.controllers.SearchService;

import java.util.function.Supplier;

public class ConnectionFactoryRMI implements IConnectionFactoryRMI {

    private Supplier<SearchService> serviceInstanceSupplier;

    public ConnectionFactoryRMI(Supplier<SearchService> serviceInstanceSupplier) {
        this.serviceInstanceSupplier = serviceInstanceSupplier;
    }

    @Override
    public SearchService createSearchService() {
        return serviceInstanceSupplier.get();
    }

    @Override
    public Object createBookingService() {
        return null;
    }
}

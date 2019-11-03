package at.fhv.itb17.s5.teamb.controllers.rmi;

import at.fhv.itb17.s5.teamb.controllers.SearchService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Supplier;

public class ConnectionFactoryRMI implements IConnectionFactoryRMI {

    private Supplier<SearchService> serviceInstanceSupplier;

    public ConnectionFactoryRMI(Supplier<SearchService> serviceInstanceSupplier) throws RemoteException {
        this.serviceInstanceSupplier = serviceInstanceSupplier;
    }

    @Override
    public SearchService createSearchService() {
        return serviceInstanceSupplier.get();
    }

}

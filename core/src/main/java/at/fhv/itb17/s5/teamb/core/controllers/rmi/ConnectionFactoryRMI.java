package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Supplier;

public class ConnectionFactoryRMI extends UnicastRemoteObject implements IConnectionFactoryRMI {

    private Supplier<SearchService> serviceInstanceSupplier;

    public ConnectionFactoryRMI(Supplier<SearchService> serviceInstanceSupplier) throws RemoteException {
        this.serviceInstanceSupplier = serviceInstanceSupplier;
    }

    @Override
    public SearchService createSearchService() {
        return serviceInstanceSupplier.get();
    }

}

package at.fhv.itb17.s5.teamb.controllers.rmi;

import at.fhv.itb17.s5.teamb.controllers.EntryPoint;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EntryPointRMI extends EntryPoint {

    private ConnectionFactoryRMI factoryRMI;

    public EntryPointRMI(int port, Object coreImpl) {
        super(coreImpl);
        factoryRMI = new ConnectionFactoryRMI(SearchServiceRMI::new);
    }

    @Override
    public void start() {
        System.setProperty("java.rmi.server.codebaseOnly", String.valueOf(false));
        System.setProperty("java.security.policy", "./client.policy");

        SecurityManager sm = new SecManagerRMI();
        System.setSecurityManager(sm);
        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(2345);
            ConnectionFactoryRMI stub = (ConnectionFactoryRMI) UnicastRemoteObject.exportObject(factoryRMI, 2345);
            registry.bind("factory", stub);
        } catch (RemoteException | AlreadyBoundException e1) {
            e1.printStackTrace();
        }
        System.out.println("Started RMI");

    }

    @Override
    public void destroy() {

    }
}

package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;
import at.fhv.itb17.s5.teamb.core.controllers.general.SearchService;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Supplier;

public class EntryPointRMI extends EntryPoint {

    public static final String FACTORY_BIND_NAME = "factory";

    private ConnectionFactoryRMI factoryRMI;
    private int regPort;

    //TODO Replace Injector with concrete class
    public EntryPointRMI(int port, CoreServiceInjector coreImpl) throws RemoteException {
        super(coreImpl);
        this.regPort = port;
        factoryRMI = new ConnectionFactoryRMI(() -> {
            try {
                return new SearchServiceRMI(coreImpl.getSearchServiceCore());
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void start() throws RemoteException {
        System.setProperty("java.rmi.server.codebaseOnly", String.valueOf(false));
        System.setProperty("javax.management.MBeanTrustPermission", "register");
        System.setProperty("java.security.policy", "./client.policy");

        SecurityManager sm = new SecManagerRMI();
        System.setSecurityManager(sm);
        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(regPort);
            UnicastRemoteObject.unexportObject(factoryRMI, true);
            IConnectionFactoryRMI stub = (IConnectionFactoryRMI) UnicastRemoteObject.exportObject(factoryRMI, regPort);
            registry.rebind(FACTORY_BIND_NAME, stub);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        System.out.println("Started RMI");
    }

    @Override
    public void destroy() {

    }
}

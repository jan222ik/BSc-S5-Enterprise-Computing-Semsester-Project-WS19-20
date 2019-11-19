package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import at.fhv.itb17.s5.teamb.core.controllers.general.ClientSessionRMI;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EntryPointRMI extends EntryPoint {

    public static final String FACTORY_BIND_NAME = "factory";

    private ConnectionFactoryRMI factoryRMI;
    private int regPort;


    public EntryPointRMI(int port, CoreServiceInjector coreImpl) throws RemoteException {
        super(coreImpl);
        this.regPort = port;
        factoryRMI = new ConnectionFactoryRMI(() -> {
            try {
                return new SearchServiceRMI(coreImpl.getSearchServiceCore(), coreImpl.getEntityRepo());
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }, (ClientSessionRMI client) -> {
            try {
                return new BookingServiceRMI(coreImpl.getBookingServiceCore(), client, coreImpl.getEntityRepo());
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }, coreImpl.getAuthManagerCore());
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

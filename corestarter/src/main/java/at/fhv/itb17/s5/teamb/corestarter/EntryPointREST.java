package at.fhv.itb17.s5.teamb.corestarter;

import at.fhv.itb17.s5.teamb.apis.rest.Swagger2SpringBoot;
import at.fhv.itb17.s5.teamb.apis.rest.api.InjectHolder;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjector;

import java.rmi.RemoteException;

public class EntryPointREST extends EntryPoint {

    public EntryPointREST(CoreServiceInjector coreImpl) {
        super(coreImpl);
        InjectHolder.injector = coreImpl;
    }

    @Override
    public void start() throws RemoteException {
        try {
            Swagger2SpringBoot.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}

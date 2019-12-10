package at.fhv.itb17.s5.teamb.core.controllers.rest;

import at.fhv.itb17.s5.teamb.apis.rest.Swagger2SpringBoot;
import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;

import java.rmi.RemoteException;

public class EntryPointREST extends EntryPoint {

    public EntryPointREST(Object coreImpl) {
        super(coreImpl);
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

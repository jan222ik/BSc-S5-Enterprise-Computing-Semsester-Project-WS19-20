package at.fhv.itb17.s5.teamb.controllers;

import java.rmi.RemoteException;

public abstract class EntryPoint {

    private Object coreImpl;

    public EntryPoint(Object coreImpl) {
        this.coreImpl = coreImpl;
    }

    public abstract void start() throws RemoteException;
    public abstract void destroy();
}

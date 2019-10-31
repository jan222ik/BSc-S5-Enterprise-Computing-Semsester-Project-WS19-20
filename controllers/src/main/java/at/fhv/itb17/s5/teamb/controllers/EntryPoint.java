package at.fhv.itb17.s5.teamb.controllers;

public abstract class EntryPoint {

    private Object coreImpl;

    public EntryPoint(Object coreImpl) {
        this.coreImpl = coreImpl;
    }

    public abstract void start();
    public abstract void destroy();
}

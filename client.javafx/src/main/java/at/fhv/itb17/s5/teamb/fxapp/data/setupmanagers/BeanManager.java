package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;

import java.rmi.RemoteException;

public class BeanManager implements SetupManager {
    @Override
    public boolean create() throws RemoteException {
        return false;
    }

    @Override
    public RMIConnectionStatus connect(String host, int port) {
        return null;
    }

    @Override
    public RMIConnectionStatus authenticate(String user, String pwd) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void setCallbackConsumer(SetupCallback callbackConsumer) {

    }
}

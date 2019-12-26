package at.fhv.itb17.s5.teamb.core.controllers.general;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FrontEndClient extends UnicastRemoteObject implements IFrontEndClient {
    public FrontEndClient() throws RemoteException {
        //Object for possible RMI Callbacks
    }
}

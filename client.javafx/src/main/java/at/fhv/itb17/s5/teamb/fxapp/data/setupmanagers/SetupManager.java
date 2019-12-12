package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.rmi.RemoteException;
import java.util.List;

public interface SetupManager {
    boolean create() throws RemoteException;

    RMIConnectionStatus connect(String host, int port);

    RMIConnectionStatus authenticate(String user, String pwd);

    void close();

    void setCallbackConsumer(SetupCallback callbackConsumer);

    void setMsgTopics();

    void initMsgAsync(String clientId);
}

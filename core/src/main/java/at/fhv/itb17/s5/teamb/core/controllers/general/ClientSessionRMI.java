package at.fhv.itb17.s5.teamb.core.controllers.general;

import java.rmi.server.UnicastRemoteObject;

public class ClientSessionRMI {
    private String username;
    private String password;
    private UnicastRemoteObject client;

    public ClientSessionRMI(String username, String password, UnicastRemoteObject client) {
        this.username = username;
        this.password = password;
        this.client = client;
    }
}

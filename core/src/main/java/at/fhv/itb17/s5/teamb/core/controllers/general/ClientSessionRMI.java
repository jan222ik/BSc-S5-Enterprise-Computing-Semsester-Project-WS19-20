package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.persistence.entities.Client;

public class ClientSessionRMI {
    private String username;
    private String password;
    private IFrontEndClient clientFXCallback;
    private Client client;

    public ClientSessionRMI(String username, String password, IFrontEndClient clientFXCallback, Client client) {
        System.out.println("client = " + clientFXCallback);
        this.username = username;
        this.password = password;
        this.clientFXCallback = clientFXCallback;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}

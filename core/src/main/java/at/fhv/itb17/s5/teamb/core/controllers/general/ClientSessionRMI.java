package at.fhv.itb17.s5.teamb.core.controllers.general;

import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRoles;

public class ClientSessionRMI {
    private String username;
    private String password;
    private IFrontEndClient client;

    public ClientSessionRMI(String username, String password, IFrontEndClient client) {
        System.out.println("client = " + client);
        this.username = username;
        this.password = password;
        this.client = client;
    }

    public Client getClient() {
        return new Client(username, username, ClientRoles.EXTERNAL, null); //TODO Impl
    }
}

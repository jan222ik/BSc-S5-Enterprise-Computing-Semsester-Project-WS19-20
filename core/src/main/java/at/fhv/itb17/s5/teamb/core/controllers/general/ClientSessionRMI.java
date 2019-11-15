package at.fhv.itb17.s5.teamb.core.controllers.general;

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
}

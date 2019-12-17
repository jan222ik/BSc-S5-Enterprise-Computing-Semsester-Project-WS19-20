package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;

public class ClientRepository {

    private EntityRepository ep;

    public ClientRepository(EntityRepository ep) {
        this.ep = ep;
    }

    public Client getByUsername(String username) {
        return ep.get(Client.class, username);
    }

    public ClientRole getWebRole() {
        return  ep.get(ClientRole.class, "WEB").cloneObj();
    }

    public void addClient(Client client) {
        ep.saveOrUpdate(client);
    }
}

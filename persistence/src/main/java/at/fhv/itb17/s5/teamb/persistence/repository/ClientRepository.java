package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Client;

public class ClientRepository {
    private EntityRepository ep;

    public ClientRepository(EntityRepository ep) {
        this.ep = ep;
    }

    public Client getByUsername(String username) {
        return ep.get(Client.class, username);
    }
}

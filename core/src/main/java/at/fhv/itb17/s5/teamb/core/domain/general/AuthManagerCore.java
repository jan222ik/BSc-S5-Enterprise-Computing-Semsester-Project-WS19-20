package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.persistence.entities.Address;
import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.ClientRole;
import at.fhv.itb17.s5.teamb.persistence.repository.ClientRepository;
import at.fhv.itb17.s5.teamb.util.LDAP;

import javax.naming.NamingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class AuthManagerCore {

    private final LDAP ldap;
    private final HashMap<String, Pair> backdoorUsers;
    private final ClientRepository clientRepository;
    private final boolean withBackdoorUsers;


    public AuthManagerCore(boolean withBackdoorUsers, boolean withLDAP, ClientRepository clientRepository) {
        this.withBackdoorUsers = withBackdoorUsers;
        this.ldap = (withLDAP) ? new LDAP() : null;
        this.clientRepository = clientRepository;
        backdoorUsers = new HashMap<>();
        if (withBackdoorUsers) {
            backdoorUsers.put("backdoor",
                    new Pair("backdoorPWD",
                            new Client("backdoor", "Door, Back",
                                    Collections.singletonList(
                                            new ClientRole("ADMIN", true, true, 0)
                                    ),
                                    new LinkedList<>(),
                                    new Address("AT", "6850", "Dornbirn", "Sägerstr.", "1"))));
        }
    }

    public boolean check(String username, String password) {
        try {
            boolean b = false;
            if (ldap != null) {
                b = ldap.areCredentialsCorrect(username, password);
            }
            if (!b && backdoorUsers.containsKey(username)) {
                b = backdoorUsers.get(username).pwd.equals(password);
            }
            return b;
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Client queryClient(String username) {
        Client ret = null;
        if (clientRepository != null) {
            ret = clientRepository.getByUsername(username);
        }
        if (ret == null && withBackdoorUsers && backdoorUsers.containsKey(username)) {
            ret = backdoorUsers.get(username).client;
        }
        return ret;
    }

    private static class Pair {
        String pwd;
        Client client;

        Pair(String pwd, Client client) {
            this.pwd = pwd;
            this.client = client;
        }
    }

}

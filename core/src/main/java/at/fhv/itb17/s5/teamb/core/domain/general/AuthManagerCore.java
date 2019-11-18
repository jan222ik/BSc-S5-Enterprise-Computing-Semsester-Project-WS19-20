package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.util.LDAP;

import javax.naming.NamingException;
import java.util.HashMap;

public class AuthManagerCore {

    private final LDAP ldap;
    private final HashMap<String, String> backdoorUsers;

    public AuthManagerCore(boolean withBackdoorUsers) {
        this.ldap = new LDAP();
        backdoorUsers = new HashMap<>();
        if (withBackdoorUsers) {
            backdoorUsers.put("backdoor", "backdoorPWD");
        }
    }

    public boolean check(String username, String password) {
        try {
            boolean b = ldap.areCredentialsCorrect(username, password);
            if (!b && backdoorUsers.containsKey(username)) {
                b = backdoorUsers.get(username).equals(password);
            }
            return b;
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }
    }
}

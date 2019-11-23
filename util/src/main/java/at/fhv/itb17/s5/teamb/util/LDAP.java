package at.fhv.itb17.s5.teamb.util;

import org.jetbrains.annotations.NotNull;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAP {

    DirContext getLDAPConnection(String commonName, String organisationUnit, String password) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://dc01.ad.uclv.net:636/OU=fhusers,DC=ad,DC=uclv,DC=net");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, String.format("CN=%s,OU=%s,DC=ad,DC=uclv,DC=net", commonName, organisationUnit));
        env.put(Context.SECURITY_CREDENTIALS, password);
        return new InitialDirContext(env);
    }

    public boolean areCredentialsCorrect(String username, String password) throws NamingException {
        DirContext ldapConnection = getLDAPConnection("tf-test2", "SpecialUsers", "1d48oOffxMXb");
        String commonName = search((InitialDirContext) ldapConnection, username);
        boolean validUser = true;
        try {
            getLDAPConnection(commonName, "fhusers", password);
        } catch (AuthenticationException e) {
            validUser = false;
        }
        //e.g. if user is tf-test
        if (!validUser) {
            try {
                getLDAPConnection(username, "SpecialUsers", password);
                validUser = true;
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        return validUser;
    }

    private String search(@NotNull InitialDirContext ctx, String username) throws NamingException {
        Attributes match = new BasicAttributes("userprincipalname", username);
        NamingEnumeration<SearchResult> search = ctx.search("", match);
        if (search.hasMore()) {
            SearchResult next = search.next();
            return next.getAttributes().get("cn").get().toString();
        }
        return "";
    }
}

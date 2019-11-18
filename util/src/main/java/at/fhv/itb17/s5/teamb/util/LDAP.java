package at.fhv.itb17.s5.teamb.util;

import org.jetbrains.annotations.NotNull;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

public class LDAP {

    private DirContext getLDAPConnection(String commonName, String organisationUnit, String password) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://dc01.ad.uclv.net:636/OU=fhusers,DC=ad,DC=uclv,DC=net");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, String.format("CN=%s,OU=%s,DC=ad,DC=uclv,DC=net", commonName, organisationUnit));
        env.put(Context.SECURITY_CREDENTIALS, password);
        return new InitialDirContext(env);
    }

    //arg[0] = mle2266@students.fhv.at - or other similar username
    //arg[1] = password
    public static void main(String... args) throws NamingException {
        LDAP ldap = new LDAP();
        System.out.println(ldap.areCredentialsCorrect(args[0], args[1]));
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

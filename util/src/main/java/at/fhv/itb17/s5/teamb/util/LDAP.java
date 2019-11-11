package at.fhv.itb17.s5.teamb.util;

import javax.naming.*;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LDAP {

    public static DirContext getLDAPConnection(String surname, String forename, String username, String password) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://dc01.ad.uclv.net:636/OU=fhusers,DC=ad,DC=uclv,DC=net");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, String.format("CN=%s %s - %s,OU=fhusers,DC=ad,DC=uclv,DC=net", surname, forename, username));
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            DirContext ctx = new InitialDirContext(env);
            System.out.println("connected");
            return ctx;
        } catch (AuthenticationNotSupportedException ex) {
            System.out.println("The authentication is not supported by the server");
            ex.printStackTrace();
        } catch (AuthenticationException ex) {
            System.out.println("incorrect password or username");
            ex.printStackTrace();
        } catch (NamingException ex) {
            System.out.println("error when trying to create the context");
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String... args) throws NamingException {
        DirContext ldapConnection = getLDAPConnection(args[0], args[1], args[2], args[3]);
        browseRecursive(ldapConnection, 100);
    }

    private static void browseRecursive(Context ctx, int depth) throws NamingException {
        NamingEnumeration<Binding> namingEnum = ctx.listBindings("");
        for (int j = 0; j < 100 && namingEnum.hasMore(); j++) {
            Binding bnd = namingEnum.next();
            if (bnd.getObject() instanceof Context) {
                System.out.println(bnd.getName());
                Context curCtx = (Context) bnd.getObject();
                browseRecursive(curCtx, depth + 1);
            }
        }
    }
}

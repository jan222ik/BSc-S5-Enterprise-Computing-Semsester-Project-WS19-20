package at.fhv.itb17.s5.teamb.util;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import static org.hamcrest.MatcherAssert.assertThat;

public class LDAPTest {

    private final static String VALID_USER = "tf-test2";
    private final static String VALID_PWD = "1d48oOffxMXb";
    private final static String INVALID_USER = "invalidTest";
    private final static String INVALID_PWD = "invalidTest";

    @Test
    @DisplayName("LDAP - Connection: Success")
    public LDAP testLDAPConnection() throws NamingException {
        LDAP ldap = new LDAP();
        assertThat(ldap.getLDAPConnection(VALID_USER, "SpecialUsers", VALID_PWD), Matchers.notNullValue());
        return ldap;
    }

    @Test
    @DisplayName("LDAP - Connection: Fail - Invalid User")
    public void testLDAPConnection2() throws NamingException {
        LDAP ldap = new LDAP();
        try {
            ldap.getLDAPConnection(INVALID_USER, "SpecialUsers", VALID_PWD);
            Assertions.fail();
        } catch (Exception e) {
            assertThat(e instanceof AuthenticationException, Matchers.is(true));
        }
    }

    @Test
    @DisplayName("LDAP - Connection: Fail - Empty Password")
    public void testLDAPConnectio4711() {
        LDAP ldap = new LDAP();
        try {
            ldap.getLDAPConnection(INVALID_USER, "SpecialUsers", "");
            Assertions.fail();
        } catch (Exception e) {
            assertThat(e instanceof AuthenticationException, Matchers.is(true));
        }
    }

    @Test
    @DisplayName("LDAP - Connection: Fail - Invalid Password")
    public void testLDAPConnection3() throws NamingException {
        LDAP ldap = new LDAP();
        try {
            ldap.getLDAPConnection(VALID_USER, "SpecialUsers", INVALID_PWD);
            Assertions.fail();
        } catch (Exception e) {
            assertThat(e instanceof AuthenticationException, Matchers.is(true));
        }
    }


    @Test
    @DisplayName("LDAP - User Credentials: Fail - Invalid User")
    public void checkUserCred() throws NamingException {
        LDAP ldap = testLDAPConnection();
        assertThat(ldap.areCredentialsCorrect(INVALID_USER, VALID_PWD), Matchers.is(false));
    }

    @Test
    @DisplayName("LDAP - User Credentials: Fail - Invalid User")
    public void checkUserCred2() throws NamingException {
        LDAP ldap = testLDAPConnection();
        assertThat(ldap.areCredentialsCorrect(VALID_USER, INVALID_PWD), Matchers.is(false));
    }

    @Test
    @DisplayName("LDAP - User Credentials: Success")
    public void checkUserCred3() throws NamingException {
        LDAP ldap = testLDAPConnection();
        String fhvUser = System.getProperty("fhvUser");
        String fhvUserPWD = System.getProperty("fhvPWD");
        if (fhvUser == null || fhvUserPWD == null) {
            System.out.println("Skipped test - please provide -DfhvUser and -DfhvPWD in JVM Arguments");
            return;
        }
        assertThat(ldap.areCredentialsCorrect(fhvUser, fhvUserPWD), Matchers.is(true));
    }

    @Test
    @DisplayName("LDAP - SpecialUser Credentials: Success - Valid User")
    public void checkSpecialUserCred() throws NamingException {
        LDAP ldap = testLDAPConnection();
        assertThat(ldap.areCredentialsCorrect(VALID_USER, VALID_PWD), Matchers.is(true));
    }


}

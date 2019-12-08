package at.fhv.itb17.s5.teamb.persistence.entities;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

class ClientRoleTest {

    @Test
    void calcEffectiveRole() {
        ClientRole expectedRole = new ClientRole("Effective of [c,b,a]", true, true, 2);
        ClientRole effectiveRole = ClientRole.calcEffectiveRole(Arrays.asList(
                new ClientRole("a", null, true, 0),
                new ClientRole("b", false, null, 1),
                new ClientRole("c", true, true, 2)
        ));
        assertThat(effectiveRole, Matchers.is(expectedRole));
    }
}

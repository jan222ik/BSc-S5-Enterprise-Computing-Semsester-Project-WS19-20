package at.ftv.itb17.s5.teamb;

import at.fhv.itb17.s5.teamb.persistence.Main;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;

public class ExecTest {


    @Test
    public void test() {
        new Main().main(new LinkedList<>());
        assertThat(true, Matchers.is(true));
    }
}

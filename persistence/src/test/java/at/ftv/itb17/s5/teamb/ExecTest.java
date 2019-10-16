package at.ftv.itb17.s5.teamb;

import at.fhv.itb17.s5.teamb.persistence.Main;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;

public class ExecTest {


    @Test
    public void test() {
        new Main().main(new LinkedList<>());
    }
}

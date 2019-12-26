package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIController;
import org.junit.jupiter.api.BeforeAll;

import java.rmi.RemoteException;

public class TestRMI {

    private static RMIController rmi;

    @BeforeAll
    public static void beforeAll() throws RemoteException {
        rmi = new RMIController();
        rmi.connect("localhost", 2345);
    }

    //@Test

    public void checkRMI() {
        System.out.println("RUN");
       // boolean successfulLogin = rmi.doLoginBooking("hans@peter.com", "Wurscht");
       // System.out.println("successfulLogin = " + successfulLogin);
    }
}

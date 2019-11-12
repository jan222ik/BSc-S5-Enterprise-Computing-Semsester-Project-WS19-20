package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMISearchServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

public class TestRMI {

    private static RMISearchServiceImpl rmi;

    @BeforeAll
    public static void beforeAll() throws RemoteException {
        rmi = new RMISearchServiceImpl("localhost", 2345);
    }

    //@Test

    public void checkRMI() {
        System.out.println("RUN");
        boolean successfulLogin = rmi.doLoginBooking("hans@peter.com", "Wurscht");
        System.out.println("successfulLogin = " + successfulLogin);
    }
}

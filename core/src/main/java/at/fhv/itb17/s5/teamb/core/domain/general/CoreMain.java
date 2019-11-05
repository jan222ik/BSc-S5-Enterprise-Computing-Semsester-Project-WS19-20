package at.fhv.itb17.s5.teamb.core.domain.general;

import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.EntryPointRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;

public class CoreMain {

    private static final Logger logger = LogManager.getLogger(CoreMain.class);

    private void start(LinkedList<EntryPoint> entryPoints) throws RemoteException {
        for (EntryPoint entryPoint : entryPoints) {
            entryPoint.start();
        }
    }


    public static void main(String[] args) {
        logger.info("Starting Core");
        int rmiPort = 2345; //TODO add args parser
        CoreMain coreMain = new CoreMain();
        EntryPointRMI entryPointRMI;
        try {
            entryPointRMI = new EntryPointRMI(rmiPort, new CoreServiceInjectorImpl());
            LinkedList<EntryPoint> entryPoints = new LinkedList<>(Arrays.asList(entryPointRMI));
            coreMain.start(entryPoints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        logger.info("Finished Init Core");
    }
}

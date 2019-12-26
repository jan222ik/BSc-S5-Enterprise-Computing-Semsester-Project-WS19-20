package at.fhv.itb17.s5.teamb.corestarter;

import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.util.ArgumentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;

public class CoreMain {

    private static final Logger logger = LogManager.getLogger(CoreMain.class);
    private static ArgumentParser args = new ArgumentParser();

    private void start(LinkedList<EntryPoint> entryPoints) throws RemoteException {
        for (EntryPoint entryPoint : entryPoints) {
            entryPoint.start();
        }
    }


    public static void main(String... arguments) {
        logger.info("Starting Core");
        args.parseArgs(Arrays.asList(arguments), '=');
        int rmiPort = Integer.parseInt(args.getArgValue("-rmiport", "2345"));
        boolean noLDAP = args.containsKeyword("-noLDAP");
        CoreMain coreMain = new CoreMain();
        EntryPointRMI entryPointRMI;
        CoreServiceInjectorImpl coreServiceInjector = new CoreServiceInjectorImpl(!noLDAP);
        try {
            entryPointRMI = new EntryPointRMI(rmiPort, coreServiceInjector);
            LinkedList<EntryPoint> entryPoints = new LinkedList<>(Arrays.asList(new EntryPointREST(coreServiceInjector)
                    , entryPointRMI
            ));
            coreMain.start(entryPoints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        logger.info("Finished Init Core");
    }
}

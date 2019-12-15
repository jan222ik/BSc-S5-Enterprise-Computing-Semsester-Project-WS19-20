import at.fhv.itb17.s5.teamb.core.controllers.general.EntryPoint;
import at.fhv.itb17.s5.teamb.core.controllers.rmi.EntryPointRMI;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreMain;
import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.corebeans.EntryPointEJB;
import at.fhv.itb17.s5.teamb.util.ArgumentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BeanMain {

    private static final Logger logger = LogManager.getLogger(BeanMain.class);
    private static ArgumentParser args = new ArgumentParser();

    private void start(LinkedList<EntryPoint> entryPoints) throws RemoteException {
        for (EntryPoint entryPoint : entryPoints) {
            entryPoint.start();
        }
    }
    public static void main(String... arguments) {
        logger.info("Starting Core-Beans");
        args.parseArgs(Arrays.asList(arguments), '=');
//        int rmiPort = Integer.parseInt(args.getArgValue("-rmiport", "2345"));
        boolean noLDAP = args.containsKeyword("-noLDAP");
        BeanMain beanMain = new BeanMain();
        EntryPointEJB entryPoint;
        try {
            entryPoint = new EntryPointEJB(new CoreServiceInjectorImpl(!noLDAP));
            LinkedList<EntryPoint> entryPoints = new LinkedList<>(Collections.singletonList(entryPoint));
            beanMain.start(entryPoints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        logger.info("Finished Init Core");
    }

}

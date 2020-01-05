package at.fhv.itb17.s5.teamb.core.controllers.rmi;

import java.security.Permission;

public class SecManagerRMI extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        // Nothing -> Allow all
    }
}

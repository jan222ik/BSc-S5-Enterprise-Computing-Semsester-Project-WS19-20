package at.fhv.itb17.s5.teamb.controllers.rmi;

import java.security.Permission;

public class SecManagerRMI extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
    }
}

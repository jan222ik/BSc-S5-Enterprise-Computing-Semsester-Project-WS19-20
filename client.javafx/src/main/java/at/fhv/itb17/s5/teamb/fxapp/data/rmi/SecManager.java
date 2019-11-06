package at.fhv.itb17.s5.teamb.fxapp.data.rmi;

import java.security.Permission;

public class SecManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
    }
}

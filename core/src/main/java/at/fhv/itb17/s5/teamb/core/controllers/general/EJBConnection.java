package at.fhv.itb17.s5.teamb.core.controllers.general;

import javax.ejb.Remote;

@Remote
public interface EJBConnection {
    boolean hasConnection();
}

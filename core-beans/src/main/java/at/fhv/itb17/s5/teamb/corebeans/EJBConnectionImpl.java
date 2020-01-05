package at.fhv.itb17.s5.teamb.corebeans;

import at.fhv.itb17.s5.teamb.core.controllers.general.EJBConnection;

import javax.ejb.Stateless;

@Stateless
public class EJBConnectionImpl implements EJBConnection {
    @Override
    public boolean hasConnection() {
        return true;
    }
}

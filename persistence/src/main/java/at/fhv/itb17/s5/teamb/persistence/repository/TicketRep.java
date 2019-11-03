package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

public class TicketRep {
    private static final EntityRepository name = new EntityRepository();


    public void saveOrUpdate(Ticket ticket) {
        name.saveOrUpdate(ticket);
    }


}

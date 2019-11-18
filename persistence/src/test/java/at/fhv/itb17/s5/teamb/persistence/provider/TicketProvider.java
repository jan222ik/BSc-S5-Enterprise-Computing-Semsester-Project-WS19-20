package at.fhv.itb17.s5.teamb.persistence.provider;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

public class TicketProvider {

    public static Ticket getUnsavedTicket() {
        return new Ticket();
    }
}

package at.fhv.itb17.s5.teamb.apis.rest.model;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.util.List;

public interface PaymentProcessor {

    boolean buy(List<Ticket> ticket2Book, PaymentInfo bookingInfo);
}

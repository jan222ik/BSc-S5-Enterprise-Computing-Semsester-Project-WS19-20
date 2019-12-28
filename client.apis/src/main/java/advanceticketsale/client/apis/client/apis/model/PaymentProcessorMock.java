package advanceticketsale.client.apis.client.apis.model;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.util.List;

public class PaymentProcessorMock implements PaymentProcessor {
    @Override
    public PaymentTransaction buy(List<Ticket> ticket2Book, PaymentInfo bookingInfo) {
        //Implementation out of scope
        return new PaymentTransaction();
    }
}

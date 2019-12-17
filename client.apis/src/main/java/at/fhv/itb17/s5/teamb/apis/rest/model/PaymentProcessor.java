package at.fhv.itb17.s5.teamb.apis.rest.model;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.util.List;

public interface PaymentProcessor {

    PaymentTransaction buy(List<Ticket> ticket2Book, PaymentInfo bookingInfo);


    public class PaymentTransaction {

        boolean validStatus = true;

        public boolean commit() {
            // Impl not in scope
            return true;
        }

        public void abort() {
            validStatus = false;
            // Impl not in scope
        }

        public boolean isValid() {
            return validStatus;
        }
    }
}


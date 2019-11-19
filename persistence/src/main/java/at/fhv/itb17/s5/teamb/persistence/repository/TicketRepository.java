package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TicketRepository {

    private EntityRepository entityRepository;

    public TicketRepository(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Ticket bookIfFree(Ticket ticket) {
        List<Ticket> tickets = bookIfFree(Collections.singletonList(ticket));
        if (tickets != null) {
            Optional<Ticket> first = tickets.stream().findFirst();
            return first.orElse(null);
        } else {
            return null;
        }
    }

    @Nullable
    public List<Ticket> bookIfFree(List<Ticket> tickets) {
        SessionFactory sessionFactory = entityRepository.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        if (!(transaction.isActive())) {
            transaction.begin();
        }
        try {
            for (Ticket ticket : tickets) {
                LocationSeat bookedSeat = ticket.getBookedSeat();
                if (bookedSeat != null) {
                    currentSession.refresh(bookedSeat);
                    if (!bookedSeat.isTaken()) {
                        bookedSeat.setTaken(true);
                        currentSession.save(ticket);
                    } else {
                        transaction.rollback();
                        return null;
                    }
                } else {
                    int nbrOfTickets = tickets.size();
                    EventCategory eventCategory = ticket.getBookedCategory();
                    if (eventCategory != null) {
                        currentSession.refresh(eventCategory);
                        if (eventCategory.isFreeSeating()) {
                            if (eventCategory.getTotalSpace() - eventCategory.getUsedSpace() + nbrOfTickets >= 0) {
                                currentSession.detach(eventCategory);
                                currentSession.save(ticket);
                                eventCategory.incUsed(nbrOfTickets);
                                currentSession.save(eventCategory);
                            }
                        } else {
                            transaction.rollback();
                            return null;
                        }
                    } else {
                        transaction.rollback();
                        return null;
                    }
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        if (transaction.getStatus().isOneOf(TransactionStatus.COMMITTED)) {
            System.out.println("Booked successfully " + tickets.size() + " tickets");
            tickets.forEach(System.out::println);
        }
        return tickets;
    }


    /*
    int count = currentSession.createQuery("SELECT COUNT (*) FROM Ticket WHERE bookedOccurrence = :occ AND bookedCategory = :cat AND bookedRow = :row AND bookedSeat = :seat")
                        .setParameter("occ", ticket.getBookedOccurrence())
                        .setParameter("cat", ticket.getBookedCategory())
                        .setParameter("row", ticket.getBookedRow())
                        .setParameter("seat", ticket.getBookedSeat()).executeUpdate();
     */

}

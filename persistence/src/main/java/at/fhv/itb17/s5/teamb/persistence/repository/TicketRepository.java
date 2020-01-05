package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketRepository {

    private static final Logger logger = LogManager.getLogger(TicketRepository.class);

    private EntityRepository entityRepository;

    public TicketRepository(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Nullable
    public synchronized Ticket bookIfFree(Ticket ticket) {
        List<Ticket> tickets = bookIfFree(Collections.singletonList(ticket));
        if (tickets != null) {
            Optional<Ticket> first = tickets.stream().findFirst();
            return first.orElse(null);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"squid:S1168", "squid:S3776", "squid:S1905"})
    //Empty Collection for null and Cognitive Complexity
    @Nullable
    public List<Ticket> bookIfFree(List<Ticket> tickets) {
        List<Ticket> bookedTickets = new LinkedList<>();
        SessionFactory sessionFactory = entityRepository.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        if (!(transaction.isActive())) {
            transaction.begin();
        }
        try {
            for (Ticket ticket : tickets) {
                LocationSeat bookedSeat = ticket.getBookedSeat();
                if (bookedSeat != null && ticket.getBookedRow() != null) {
                    currentSession.refresh(bookedSeat);
                    if (!bookedSeat.isTaken()) {
                        bookedSeat.setTaken(true);
                        currentSession.merge(bookedSeat);
                        System.out.println("ticket = " + ticket);
                        Ticket merge = (Ticket) currentSession.merge(ticket);
                        System.out.println("merge = " + merge);
                        bookedTickets.add(merge);
                    } else {
                        transaction.rollback();
                        return null;
                    }
                } else {
                    int nbrOfTickets = tickets.size();
                    EventCategory eventCategory = ticket.getBookedCategory();
                    if (eventCategory != null && eventCategory.getEventCategoryId() != null) {
                        currentSession.refresh(eventCategory);
                        if (eventCategory.isFreeSeating()) {
                            if (eventCategory.getTotalSpace() - (eventCategory.getUsedSpace() + nbrOfTickets) >= 0) {
                                currentSession.merge(eventCategory);
                                currentSession.save(ticket);
                                eventCategory.incUsed(nbrOfTickets);
                                currentSession.saveOrUpdate(eventCategory);
                                Ticket merge = (Ticket) currentSession.merge(ticket);
                                bookedTickets.add(merge);
                            } else {
                                transaction.rollback();
                                return null;
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
        TransactionStatus status = transaction.getStatus();
        System.out.println("status = " + status);
        if (status.isOneOf(TransactionStatus.COMMITTED)) {
            logger.info("Booked successfully {} tickets", bookedTickets.size());
            for (int i = 0; i < bookedTickets.size(); i++) {
                logger.info("Ticket: [{}]: {}", i, bookedTickets.get(i));
            }
        }
        return bookedTickets;
    }
}

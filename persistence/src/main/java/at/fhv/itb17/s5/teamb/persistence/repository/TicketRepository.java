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

import java.util.ArrayList;
import java.util.Collections;
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

    @SuppressWarnings({"squid:S1168", "squid:S3776"}) //Empty Collection for null and Cognitive Complexity
    @Nullable
    public synchronized List<Ticket> bookIfFree(List<Ticket> tickets) {
        List<Ticket> ticketsToPersist = new ArrayList<>();
        try {
            for (Ticket ticket : tickets) {
                LocationSeat bookedSeat = ticket.getBookedSeat();
                if (bookedSeat != null && ticket.getBookedRow() != null) {
                    if (!bookedSeat.isTaken()) {
                        bookedSeat.setTaken(true);
                        ticketsToPersist.add(ticket);
                    } else {
                        return null;
                    }
                } else {
                    int nbrOfTickets = tickets.size();
                    EventCategory eventCategory = ticket.getBookedCategory();
                    if (eventCategory != null && eventCategory.getEventCategoryId() != null) {
                        if (eventCategory.isFreeSeating()) {
                            if (eventCategory.getTotalSpace() - (eventCategory.getUsedSpace() + nbrOfTickets) >= 0) {
                                ticket.getBookedCategory().incUsed(1);
                                ticketsToPersist.add(ticket);
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            }
            entityRepository.atomicSave(new ArrayList<>(ticketsToPersist));
            logger.info("Booked successfully {} tickets", tickets.size());
            for (int i = 0; i < tickets.size(); i++) {
                logger.info("Ticket: [{}]: {}", i, tickets.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tickets;
    }
}

package at.fhv.itb17.s5.teamb.persistence.repository;

import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;

import java.util.List;

public class TicketRepository {

    private EntityRepository entityRepository;

    public TicketRepository(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Ticket bookIfFree(Ticket ticket) {
        int i = entityRepository.hashCode();
        //TODO LERCH IMPL
        return ticket;
    }

    public List<Ticket> bookIfFree(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            bookIfFree(ticket);
        }
        return tickets;
    }


}

package at.fhv.itb17.s5.teamb.core.domain.booking;

import at.fhv.itb17.s5.teamb.persistence.entities.Client;
import at.fhv.itb17.s5.teamb.persistence.entities.Event;
import at.fhv.itb17.s5.teamb.persistence.entities.EventCategory;
import at.fhv.itb17.s5.teamb.persistence.entities.EventOccurrence;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationRow;
import at.fhv.itb17.s5.teamb.persistence.entities.LocationSeat;
import at.fhv.itb17.s5.teamb.persistence.entities.Ticket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BookingServiceCore {
    @Nullable
    Ticket bookTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat);
    @Nullable
    Ticket bookTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat, LocationRow row, LocationSeat seat);
    @Nullable
    Ticket reserveTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat);
    @Nullable
    Ticket reserveTicket(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull EventCategory cat, LocationRow row, LocationSeat seat);

    @Nullable
    List<Ticket> bookTickets(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull List<CatRowSeatWrapper> catRowSeatWrappers);
    @Nullable
    List<Ticket> reserveTickets(@NotNull Client client, @NotNull Event event, @NotNull EventOccurrence occ, @NotNull List<CatRowSeatWrapper> catRowSeatWrappers);

    @Nullable
    List<Ticket> bookTickets(List<Ticket> tickets);
}

package at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catseat;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationRowDTO;
import at.fhv.itb17.s5.teamb.dtos.LocationSeatDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.CatItemView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class CatSeatPresenter implements CatItemView {

    @Inject
    private Style style;

    @FXML
    private Label ticketBookAmountL;
    @FXML
    private VBox seatSelectVBox;
    @FXML
    private Label catNameL;
    @FXML
    private Label catPriceL;
    @FXML
    private Label catSpaceL;

    private EvCategoryInterfaceDTO cat;
    private TicketNumber ticketAmountProp;

    private HashSet<Pair> markedSeats;

    public void setData(@NotNull EvCategoryInterfaceDTO cat, @NotNull ChangeListener<Integer> changeListener) {
        this.cat = cat;
        markedSeats = new HashSet<>();
        ticketAmountProp = new TicketNumber();

        ticketAmountProp.sub((observable, oldValue, newValue) -> {
            System.out.println("Updated val=" + newValue);
            changeListener.changed(null, oldValue, newValue);
        });
        catNameL.setText(cat.getCategoryName());
        catPriceL.setText((cat.getPriceInCent() / 100) + "€");
        catSpaceL.setText(cat.getUsedTickets() + " / " + cat.getTotalTickets());
        if (cat instanceof EvCategorySeatsDTO) {
            Background free = style.SURFACE().asBackground();
            Background taken = style.BACKGROUND().asBackground();
            for (LocationRowDTO seatingRow : ((EvCategorySeatsDTO) cat).getSeatingRows()) {
                HBox hBox = new HBox();
                Label label = new Label(seatingRow.getRowIdentifier());
                label.setTextFill(style.ON_BACKGROUND().asPaint());
                hBox.getChildren().add(label);
                for (LocationSeatDTO seat : seatingRow.getSeats()) {
                    Button button = new Button(seat.getSeatIdentifier());
                    button.setBackground((seat.isFree()) ? free : taken);
                    button.setTextFill(style.ON_SURFACE().asPaint());
                    if (seat.isFree()) {
                        button.setOnAction(e -> markSeat(button, seatingRow, seat));
                    }
                    hBox.getChildren().add(button);
                }
                seatSelectVBox.getChildren().add(hBox);
            }
        }
    }

    private void markSeat(Button button, LocationRowDTO rowDTO, LocationSeatDTO seatDTO) {
        Pair pair = new Pair(rowDTO, seatDTO);
        if (markedSeats.contains(pair)) {
            markedSeats.remove(pair);
            ticketAmountProp.dec();
            button.setBackground(style.SURFACE().asBackground());
            button.setTextFill(style.ON_SURFACE().asPaint());
        } else {
            markedSeats.add(pair);
            ticketAmountProp.inc();
            button.setBackground(style.SECONDARY().asBackground());
            button.setTextFill(style.ON_SECONDARY().asPaint());
        }
    }

    @Override
    public int getTicketAmount() {
        return ticketAmountProp.getNumber();
    }

    @Override
    public EvCategoryInterfaceDTO getCat() {
        return cat;
    }

    @Override
    public List<TicketDTO> getTickets(EventDTO eventDTO, EvOccurrenceDTO occ) {
        List<TicketDTO> ticketDTOs = new LinkedList<>();
        for (Pair rowSeat : markedSeats) {
            ticketDTOs.add(new TicketDTO(eventDTO, occ, (EvCategorySeatsDTO) cat, rowSeat.row, rowSeat.seat));
        }
        return ticketDTOs;
    }


    static class Pair {
        private LocationRowDTO row;
        private LocationSeatDTO seat;

        Pair(LocationRowDTO row, LocationSeatDTO seat) {
            this.row = row;
            this.seat = seat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(row, pair.row) &&
                    Objects.equals(seat, pair.seat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, seat);
        }
    }

    static class TicketNumber {
        int number = 0;
        List<ChangeListener<Integer>> changeListeners = new LinkedList<>();

        public void sub(ChangeListener<Integer> listener) {
            changeListeners.add(listener);
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            int old = this.number;
            this.number = number;
            updateSubs(old, number);
        }

        public void inc() {
            setNumber(getNumber() + 1);
        }

        public void dec() {
            setNumber(getNumber() - 1);
        }

        private void updateSubs(int old, int now) {
            for (ChangeListener<Integer> changeListener : changeListeners) {
                changeListener.changed(null, old, now);
            }
        }
    }

}


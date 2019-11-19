package at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catfree;

import at.fhv.itb17.s5.teamb.dtos.EvCategoryFreeDTO;
import at.fhv.itb17.s5.teamb.dtos.EvCategoryInterfaceDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.CatItemView;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CatFreePresenter implements CatItemView {
    @FXML
    private Label catNameL;
    @FXML
    private Label catPriceL;
    @FXML
    private Label catSpaceL;
    @FXML
    private Spinner<Integer> ticketBookAmountSpinner;

    private EvCategoryInterfaceDTO cat;


    public void setData(@NotNull EvCategoryInterfaceDTO cat, @NotNull ChangeListener<Integer> changeListener) {
        this.cat = cat;
        catNameL.setText(cat.getCategoryName());
        catPriceL.setText((cat.getPriceInCent() / 100) + "€");
        catSpaceL.setText(cat.getUsedTickets() + " / " + cat.getTotalTickets());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, cat.getTotalTickets() - cat.getUsedTickets(), 0);
        ticketBookAmountSpinner.setValueFactory(valueFactory);
        ticketBookAmountSpinner.valueProperty().addListener(changeListener);
    }


    @Override
    public int getTicketAmount() {
        return Optional.ofNullable(ticketBookAmountSpinner.getValue()).orElse(0);
    }

    @Override
    public EvCategoryInterfaceDTO getCat() {
        return cat;
    }

    @Override
    public List<TicketDTO> getTickets(EventDTO eventDTO, EvOccurrenceDTO occ) {
        Integer ticketCount = Optional.ofNullable(ticketBookAmountSpinner.getValue()).orElse(0);
        LinkedList<TicketDTO> ticketDTOS = new LinkedList<>();
        for (int i = 0; i < ticketCount; i++) {
            ticketDTOS.add(new TicketDTO(eventDTO, occ, (EvCategoryFreeDTO) cat));
        }
        return ticketDTOS;
    }
}

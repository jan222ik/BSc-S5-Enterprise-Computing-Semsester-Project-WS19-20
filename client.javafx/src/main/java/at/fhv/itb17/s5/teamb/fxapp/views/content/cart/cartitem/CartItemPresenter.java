package at.fhv.itb17.s5.teamb.fxapp.views.content.cart.cartitem;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CartItemPresenter {
    @FXML
    private Label ticketAmountL;
    @FXML
    private Label evNameL;
    @FXML
    private Label occDateTimeL;
    @FXML
    private Label catNameL;
    @FXML
    private Label catPriceL;
    @FXML
    private Label catSpaceL;
    private List<TicketDTO> ticks;

    public void setData(List<TicketDTO> ticks) {
        this.ticks = ticks;
        ticketAmountL.setText(String.valueOf(ticks.size()));
        evNameL.setText(ticks.get(0).getEventDTO().getTitle());
        occDateTimeL.setText(LocalDateTime.of(ticks.get(0).getOcc().getDate(), ticks.get(0).getOcc().getTime()).format(DateTimeFormatter.ofPattern("dd.MM.yy::hh:mm")));
        catNameL.setText(ticks.get(0).getCat().getCategoryName());
        catPriceL.setText(ticks.get(0).getCat().getPriceInCent() / 100 + "€");
        catSpaceL.setText(ticks.get(0).getCat().getUsedTickets() + " / " + ticks.get(0).getCat().getTotalTickets());
    }
}

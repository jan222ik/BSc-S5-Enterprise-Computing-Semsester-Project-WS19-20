package at.fhv.itb17.s5.teamb.fxapp.views.content.booking;

import at.fhv.itb17.s5.teamb.dtos.EvCategorySeatsDTO;
import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ResultVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.CatItemView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catfree.CatFreePresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catfree.CatFreeView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catseat.CatSeatPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.bookingitem.catseat.CatSeatView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingPresenter implements ContentfulViewLifeCycle<ResultVM> {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FXML
    private Button backToResultsBtn;
    @FXML
    private Button addToCartAndContinueBtn;
    @FXML
    private Button addCartBtn;
    @FXML
    private Label eventL;
    @FXML
    private Label artistsL;
    @FXML
    private Label descriptionL;
    @FXML
    private Label dateL;
    @FXML
    private Label timeL;
    @FXML
    private Label locationL;
    @FXML
    private Label addressL;
    @FXML
    private Label organizerL;
    @FXML
    private JFXListView<Parent> categoriesLV;
    @FXML
    private Label totalPriceL;
    @FXML
    private Label totalAmountL;

    private List<CatItemView> catItemViewPresenter = new LinkedList<>();
    private EvOccurrenceDTO occ;
    private EventDTO evt;

    @Override
    public void onCreate(ResultVM viewModel, NavigationStackActions<ResultVM> navActions) {
        backToResultsBtn.setOnAction(e -> navActions.popLast().showTOS());
        addCartBtn.setOnAction(e -> {
            addToCart(viewModel);
            navActions.changeToMenuItem(ApplicationMenuViews.CART_VIEW, false);
        });
        addToCartAndContinueBtn.setOnAction(e -> {
            addToCart(viewModel);
            navActions.popLast().showTOS();
        });
    }

    private void addToCart(ResultVM viewModel) {
        List<TicketDTO> tickets = catItemViewPresenter.stream().flatMap(e -> e.getTickets(evt, occ).stream()).collect(Collectors.toList());
        viewModel.setTicketsForBooking(tickets);
    }

    public void setEventOccurrenceData(EventDTO evt, EvOccurrenceDTO occ) {
        this.evt = evt;
        this.occ = occ;
        updateDate(evt, occ);
    }

    //Extra method because it should be possible to add refresh form button or 'onReturned' later. Proposal: ViewModel.refresh(occurrence) which delegates until repo
    private void updateDate(@NotNull EventDTO evt, @NotNull EvOccurrenceDTO occ) {
        eventL.setText(evt.getTitle());
        artistsL.setText(String.join(", ", evt.getArtistNames()));
        descriptionL.setText(evt.getDescription());
        dateL.setText(occ.getDate().format(format));
        timeL.setText(occ.getTime().toString());
        locationL.setText(occ.getCountry() + ", " + occ.getCity());
        addressL.setText(String.join(" ", occ.getCountry(), ", ", occ.getCity(), ", ", occ.getZip(), "\n", occ.getStreet(), ", ", occ.getHouse()));
        organizerL.setText(evt.getOrg_name());
        categoriesLV.getItems().clear();
        final ChangeListener<Integer> changeListener = (observable, oldValue, newValue) -> updateTotals();
        List<Parent> collect = occ.getPriceCategories().stream().map(cat -> {
            if (cat instanceof EvCategorySeatsDTO) {
                CatSeatView view = new CatSeatView();
                CatSeatPresenter presenter = (CatSeatPresenter) view.getPresenter();
                catItemViewPresenter.add(presenter);
                presenter.setData(cat, changeListener);
                return view.getView();
            } else {
                CatFreeView view = new CatFreeView();
                CatFreePresenter presenter = (CatFreePresenter) view.getPresenter();
                catItemViewPresenter.add(presenter);
                presenter.setData(cat, changeListener);
                return view.getView();
            }
        }).collect(Collectors.toList());
        categoriesLV.getItems().addAll(collect);
        updateTotals();
    }

    private void updateTotals() {
        System.out.println("update" + catItemViewPresenter.size());
        int totalPrice = 0;
        int totalAmount = 0;
        for (CatItemView catItemView : catItemViewPresenter) {
            System.out.println(" catItemView.getTicketAmount(); = " + catItemView.getTicketAmount());
            totalAmount += catItemView.getTicketAmount();
            totalPrice += (catItemView.getCat().getPriceInCent()) * catItemView.getTicketAmount();
        }
        totalPriceL.setText((totalPrice / 100) + "€");
        totalAmountL.setText(String.valueOf(totalAmount));
    }
}

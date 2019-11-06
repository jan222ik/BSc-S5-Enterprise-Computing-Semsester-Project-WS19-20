package at.fhv.itb17.s5.teamb.fxapp.views.content.booking;

import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ResultVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;

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
    private JFXListView categoriesLV;

    @Override
    public void onCreate(ResultVM viewModel, NavigationStackActions<ResultVM> navActions) {
        backToResultsBtn.setOnAction(e -> navActions.popLast().showTOS());
        addCartBtn.setOnAction(e -> {
            addToCart(viewModel);
            navActions.changeToMenuItem(ApplicationMenuViews.CART_VIEW);
        });
        addToCartAndContinueBtn.setOnAction(e -> {
            addToCart(viewModel);
            navActions.popLast().showTOS();
        });
    }

    private void addToCart(ResultVM viewModel) {
        //TODO CRATE Entry for the cart in view model
    }

    public void setEventOccurrenceData(EventDTO evt, EvOccurrenceDTO occ) {
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
    }
}

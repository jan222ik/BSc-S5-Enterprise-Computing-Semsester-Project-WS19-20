package at.fhv.itb17.s5.teamb.fxapp.views.content.browser;

import at.fhv.itb17.s5.teamb.dtos.EvOccurrenceDTO;
import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ResultVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.BookingPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.booking.BookingView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.browseritem.BrowserItemPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.browseritem.BrowserItemView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BrowserPresenter implements ContentfulViewLifeCycle<ResultVM> {

    private static final Logger logger = LogManager.getLogger(BrowserPresenter.class);

    @FXML
    private VBox resultBox;
    @FXML
    private Label qString;
    @FXML
    private Label numberOfResults;
    @FXML
    private JFXListView<AnchorPane> resultLV;
    @FXML
    private Button back2FilterBtn;
    @FXML private Button refreshBtn;

    private NavigationStackActions<ResultVM> navigationStackActions = null; //Use with caution

    @Override
    public void onCreate(ResultVM viewModel, NavigationStackActions<ResultVM> navActions) {
        navigationStackActions = navActions;
        back2FilterBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.SEARCH_VIEW, false));
        refreshBtn.setOnAction(e -> this.refreshResults(viewModel));
    }

    @Override
    public void onReturned(ResultVM viewModel) {
        refreshResults(viewModel);
    }

    private void refreshResults(@NotNull ResultVM viewModel) {
        List<EventDTO> results = viewModel.getSearchResults();
        updateNumberOfResults(String.valueOf(results.size()));
        qString.setText(viewModel.getSearchString());
        resultLV.getItems().clear();
        results.stream().map(this::createBrowserItemView).forEach(v -> resultLV.getItems().add((AnchorPane) v.getView()));
    }

    @NotNull
    private BrowserItemView createBrowserItemView(EventDTO evt) {
        BrowserItemView browserItemView = new BrowserItemView();
        ((BrowserItemPresenter) browserItemView.getPresenter()).setEventData(evt, (EvOccurrenceDTO dto) -> {
            showDetailsOf(evt, dto, navigationStackActions);
        });
        return browserItemView;
    }

    private void showDetailsOf(EventDTO evt, EvOccurrenceDTO dto, NavigationStackActions<ResultVM> nav) {
        BookingView bookingView = new BookingView();
        ((BookingPresenter) bookingView.getPresenter()).setEventOccurrenceData(evt, dto);
        nav.push(bookingView);
        nav.showTOS();
    }

    private void updateNumberOfResults(String text) {
        numberOfResults.setText(text);
    }
}

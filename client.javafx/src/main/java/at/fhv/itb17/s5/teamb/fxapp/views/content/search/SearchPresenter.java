package at.fhv.itb17.s5.teamb.fxapp.views.content.search;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.SearchVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchPresenter implements ContentfulViewLifeCycle<SearchVM>, Initializable {

    @Inject
    Style style;

    @FXML
    private AnchorPane searchRootPlane;
    @FXML
    private GridPane filterGridPane;
    @FXML
    private HBox actionEmitter;
    @FXML
    private Button resetBtn;
    @FXML
    private Button searchBtn;

    @FXML
    private CheckBox fromCB;
    @FXML
    private DatePicker fromDateDP;
    @FXML
    private CheckBox tillCB;
    @FXML
    private DatePicker tillDateDP;
    @FXML
    private TextField eventTE;
    @FXML
    private TextField genreTE;
    @FXML
    private TextField artistTE;
    @FXML
    private TextField locationTF;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Background defaultBack = style.SURFACE().asBackground();
        Paint onDefaultPaint = style.ON_SURFACE().asPaint();
        searchRootPlane.setBackground(defaultBack);

        filterGridPane.setBackground(defaultBack);

        actionEmitter.setBackground(defaultBack);
        style.hoverBtn(searchBtn, defaultBack, onDefaultPaint, defaultBack, style.PRIMARY().asPaint());
        style.hoverBtn(resetBtn, defaultBack, onDefaultPaint, defaultBack, style.ERROR().asPaint());
        resetBtn.setOnAction(e -> resetFilter());
    }

    @Override
    public void onCreate(SearchVM viewModel, NavigationStackActions<SearchVM> navActions) {
        searchBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.BROWSER_VIEW, true,
                () -> NotificationsHelper.error("Error", "Could not switch to menuitem!")));
    }

    @Override
    public void onReturned(@NotNull SearchVM viewModel) {
        restore(viewModel);
    }

    @Override
    public void beforeMenuSwitch(@NotNull SearchVM viewModel) {
        viewModel.setLatestSearchViewState(saveState(viewModel.getLatestSearchViewState()));
    }

    private void restore(SearchVM viewModel) {
        SearchVM.SearchViewData state = viewModel.getLatestSearchViewState();
        if (state != null) {
            eventTE.setText(state.getEvent());
            fromCB.setSelected(state.isIncludeFrom());
            fromDateDP.setValue(state.getFromDate());
            tillCB.setSelected(state.isIncludeTill());
            tillDateDP.setValue(state.getTillDate());
            genreTE.setText(state.getGenre());
            artistTE.setText(state.getArtist());
            locationTF.setText(state.getLocation());
        } else {
            resetFilter();
            viewModel.setLatestSearchViewState(saveState(null));
        }
    }

    @NotNull
    private SearchVM.SearchViewData saveState(SearchVM.SearchViewData state) {
        if (state == null) {
            state = new SearchVM.SearchViewData();
        }
        state.setEvent(eventTE.getText());
        state.setIncludeFrom(fromCB.isSelected());
        state.setFromDate(fromDateDP.getValue());
        state.setIncludeTill(tillCB.isSelected());
        state.setTillDate(tillDateDP.getValue());
        state.setGenre(genreTE.getText());
        state.setArtist(artistTE.getText());
        state.setLocation(locationTF.getText());
        return state;
    }

    private void resetFilter() {
        eventTE.clear();
        fromCB.setSelected(false);
        fromDateDP.setValue(LocalDate.now());
        tillCB.setSelected(false);
        tillDateDP.setValue(LocalDate.now().plusMonths(1L));
        genreTE.clear();
        artistTE.clear();
        locationTF.clear();
    }
}

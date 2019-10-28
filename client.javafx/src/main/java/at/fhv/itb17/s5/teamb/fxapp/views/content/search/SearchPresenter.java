package at.fhv.itb17.s5.teamb.fxapp.views.content.search;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.SearchVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchPresenter implements ContentfulViewLifeCycle<SearchVM>, Initializable {

    private static final Logger logger = LogManager.getLogger(SearchPresenter.class);

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
    private TextField artistTE;
    @FXML
    private ChoiceBox<String> genreChoiceBox;

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
        searchBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.BROWSER_VIEW,
                () -> NotificationsHelper.error("Error", "Could not switch to menuitem!")));
    }

    @Override
    public void onReturned(@NotNull SearchVM viewModel) {
        restore(viewModel.getLatestSearchViewState());
    }

    @Override
    public void beforeMenuSwitch(@NotNull SearchVM viewModel) {
        viewModel.setLatestSearchViewState(saveState(viewModel.getLatestSearchViewState()));
    }

    private void restore(SearchVM.SearchViewData state) {
        if (state != null) {
            eventTE.setText(state.event);
            fromCB.setSelected(state.includeFrom);
            fromDateDP.setValue(state.fromDate);
            tillCB.setSelected(state.includeTill);
            tillDateDP.setValue(state.tillDate);
            genreChoiceBox.getSelectionModel().select(state.genre);
            artistTE.setText(state.artist);
        } else {
            resetFilter();
        }
    }

    @NotNull
    private SearchVM.SearchViewData saveState(SearchVM.SearchViewData state) {
        if (state == null) {
            state = new SearchVM.SearchViewData();
        }
        state.event = eventTE.getText();
        state.includeFrom = fromCB.isSelected();
        state.fromDate = fromDateDP.getValue();
        state.includeTill = tillCB.isSelected();
        state.tillDate = tillDateDP.getValue();
        state.genre = genreChoiceBox.getSelectionModel().getSelectedIndex();
        state.genreValue = genreChoiceBox.getSelectionModel().getSelectedItem();
        state.artist = artistTE.getText();
        return state;
    }

    private void resetFilter() {
        eventTE.clear();
        fromCB.setSelected(false);
        fromDateDP.setValue(LocalDate.now());
        tillCB.setSelected(false);
        tillDateDP.setValue(LocalDate.now().plusMonths(1L));
        genreChoiceBox.getSelectionModel().selectFirst();
        artistTE.clear();
    }
}

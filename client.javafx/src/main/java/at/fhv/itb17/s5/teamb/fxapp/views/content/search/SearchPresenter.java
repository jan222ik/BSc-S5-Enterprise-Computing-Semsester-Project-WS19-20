package at.fhv.itb17.s5.teamb.fxapp.views.content.search;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ContentfulViewLifeCycle;
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

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchPresenter implements ContentfulViewLifeCycle, Initializable {

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
    public DatePicker fromDateDP;
    @FXML
    private CheckBox tillCB;
    public DatePicker tillDateDP;
    @FXML
    private TextField eventTE;
    @FXML
    private TextField artistTE;
    @FXML
    private ChoiceBox genreChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Background defaultBack = style.SURFACE().asBackground();
        Paint onDefaultPaint = style.ON_SURFACE().asPaint();
        //searchRootPlane.setBackground(defaultBack);

        filterGridPane.setBackground(defaultBack);

        actionEmitter.setBackground(defaultBack);
        style.hoverBtn(searchBtn, defaultBack, onDefaultPaint, defaultBack, style.PRIMARY().asPaint());
        style.hoverBtn(resetBtn, defaultBack, onDefaultPaint, defaultBack, style.ERROR().asPaint());
        resetBtn.setOnAction(e -> resetFilter());
        resetFilter();
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

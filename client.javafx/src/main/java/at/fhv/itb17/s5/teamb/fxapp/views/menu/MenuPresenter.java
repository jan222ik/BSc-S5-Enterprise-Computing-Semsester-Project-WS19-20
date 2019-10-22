package at.fhv.itb17.s5.teamb.fxapp.views.menu;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModelImpl;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.MenuContentfulViewWrapper;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.BrowserView;
import at.fhv.itb17.s5.teamb.fxapp.views.demo.DemoView;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MenuPresenter implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuPresenter.class);

    @Inject
    private static Style style;

    private static Background background;
    private static Background backgroundError;
    private static Background backgroundSurf;

    @FXML
    private Button closeBtn;
    @FXML
    private Button maximizeBtn;
    @FXML
    private Button minimizeBtn;

    @FXML
    private
    AnchorPane contentPlane;
    @FXML
    private VBox menuVBox;

    private MenuContentfulViewWrapper current;

    @Override
    @SuppressWarnings("squid:S2696")
    public void initialize(URL location, ResourceBundle resources) {
        if (background == null) {
            background = new Background(new BackgroundFill(style.BACKGROUND_PAINT(), null, null));
        }
        if (backgroundError == null) {
            backgroundError = new Background(new BackgroundFill(style.ERROR_PAINT(), null, null));
        }
        if (backgroundSurf == null) {
            backgroundSurf = new Background(new BackgroundFill(style.SURFACE_PAINT(), null, null));
        }
        logger.debug(LogMarkers.UI_LIFECYCLE, "Init {}", MenuPresenter.class.getName());
        applyStyle();
        setupWindowListener();

        LinkedList<MenuContentfulViewWrapper> menuContentfulViewWrappers = getMenuViews();
        setMenuItems(menuContentfulViewWrappers);
        Platform.runLater(() -> switchMenuContentfulView(menuContentfulViewWrappers.getFirst()));
    }

    private void applyStyle() {
        styleButton(closeBtn, background, style.ON_BACKGROUND_PAINT(), backgroundError, style.ON_ERROR_PAINT());
        styleButton(maximizeBtn, background, style.ON_BACKGROUND_PAINT(), backgroundSurf, style.ON_SURFACE_PAINT());
        styleButton(minimizeBtn, background, style.ON_BACKGROUND_PAINT(), backgroundSurf, style.ON_SURFACE_PAINT());
        menuVBox.setBackground(background);
        menuVBox.setStyle("-fx-border-color: " + style.PRIMARY_RGB + ";\n" +
                "-fx-border-style: hidden solid hidden hidden;\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 0 2 0 0;");
        contentPlane.setBackground(background);
    }

    public void setContentView(Parent viewRootElement) {
        contentPlane.getChildren().clear();
        contentPlane.getChildren().add(viewRootElement);
    }

    private void setMenuItems(final LinkedList<MenuContentfulViewWrapper> views) {
        views.forEach(e -> {
            menuVBox.getChildren().add(e.createMenuItemView(() -> {
                logger.debug(LogMarkers.UI_EVENT, "MenuItem clicked");
                switchMenuContentfulView(e);
            }, menuVBox.widthProperty()).getView());
            e.isCurrentMenuItem(false);
        });
    }

    public void switchMenuContentfulView(int itemNumber) {
        switchMenuContentfulView(this.getMenuViews().get(itemNumber));
    }

    public void switchMenuContentfulView(MenuContentfulViewWrapper view) {
        if (current != null) {
            current.beforeMenuSwitch();
            current.isCurrentMenuItem(false);
        }
        current = view;
        view.isCurrentMenuItem(true);
        logger.debug(LogMarkers.UI, "Switching to {}", view);
        Stage stage = (Stage) contentPlane.getScene().getWindow();
        stage.setTitle(view.getTitle());
        view.showTOS();
    }

    private LinkedList<MenuContentfulViewWrapper> _applicationViews;

    private LinkedList<MenuContentfulViewWrapper> getMenuViews() {
        if (_applicationViews == null) {
            MenuContentfulViewWrapper<ViewModelImpl> item1 =
                    new MenuContentfulViewWrapper<>(new DemoView(), new ViewModelImpl(), "Demo Item 1", "Demo Content Title 1", this);
            MenuContentfulViewWrapper<ViewModelImpl> item2 =
                    new MenuContentfulViewWrapper<>(new BrowserView(), new ViewModelImpl(), "Event Browser", "Event Browser", this);
            MenuContentfulViewWrapper<ViewModelImpl> item3 =
                    new MenuContentfulViewWrapper<>(new DemoView(), new ViewModelImpl(), "Demo Item 3", "Demo Content Title 3", this);
            _applicationViews = new LinkedList<>(Arrays.asList(item1, item2, item3));
        }
        return _applicationViews;
    }

    private void setupWindowListener() {
        closeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "EXIT pressed");
            Platform.exit();
        });
        maximizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MAXIMIZE pressed");
            Stage stage = (Stage)((Button)e.getSource()).getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        });
        minimizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MINIMIZE pressed");
            Stage stage = (Stage)((Button)e.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
    }

    private void styleButton(Button btn, Background defaultB, Paint onDefaultB, Background hoverB, Paint onHoverB) {
        btn.setBackground(defaultB);
        btn.setTextFill(onDefaultB);
        btn.setOnMouseEntered(e -> {
            btn.setBackground(hoverB);
            btn.setTextFill(onHoverB);
        });
        btn.setOnMouseExited(e -> {
            btn.setBackground(defaultB);
            btn.setTextFill(onDefaultB);
        });

    }
}

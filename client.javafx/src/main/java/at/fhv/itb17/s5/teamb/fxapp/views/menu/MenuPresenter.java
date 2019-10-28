package at.fhv.itb17.s5.teamb.fxapp.views.menu;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.SearchVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModelImpl;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.MenuContentfulViewWrapper;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.BrowserView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.search.SearchView;
import at.fhv.itb17.s5.teamb.fxapp.views.demo.DemoView;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.net.URL;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MenuPresenter implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuPresenter.class);

    @Inject
    private static Style style;

    private static Background background;
    private static Background backgroundError;
    private static Background backgroundSurf;

    private MouseEvent originEvent;
    private double xOffset;
    private double yOffset;

    @FXML
    private Button closeBtn;
    @FXML
    private Button maximizeBtn;
    @FXML
    private Button minimizeBtn;

    @FXML
    private HBox separatorH;
    @FXML
    private VBox separatorV;
    @FXML
    private AnchorPane contentPlane;
    @FXML
    private VBox menuVBox;
    @FXML
    private HBox menubarHBox;
    @FXML
    private Label menubarTitle;
    @FXML
    private Button glyphHostBtn;
    @FXML
    private FontAwesomeIconView hamburgerIcon;

    private MenuContentfulViewWrapper current;

    @Override
    @SuppressWarnings("squid:S2696")
    public void initialize(URL location, ResourceBundle resources) {
        if (background == null) {
            background = style.BACKGROUND().asBackground();
        }
        if (backgroundError == null) {
            backgroundError = style.ERROR().asBackground();
        }
        if (backgroundSurf == null) {
            backgroundSurf = style.SURFACE().asBackground();
        }
        logger.debug(LogMarkers.UI_LIFECYCLE, "Init {}", MenuPresenter.class.getName());
        applyStyle();
        setupWindowListener();
        glyphHostBtn.setOnAction(this::toggleMenuList);
        LinkedList<MenuContentfulViewWrapper> menuContentfulViewWrappers = new LinkedList<>(getMenuViews().values());
        setMenuItems(menuContentfulViewWrappers);
        Platform.runLater(() -> switchMenuContentfulView(menuContentfulViewWrappers.getFirst()));
    }

    private void applyStyle() {
        style.hoverBtn(closeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundError, style.ON_ERROR().asPaint());
        style.hoverBtn(maximizeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        style.hoverBtn(minimizeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        style.hoverBtn(glyphHostBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        hamburgerIcon.setFill(style.ON_BACKGROUND().asPaint());
        menubarTitle.setTextFill(style.ON_BACKGROUND().asPaint());
        menubarHBox.setBackground(background);
        menuVBox.setBackground(background);
        separatorH.setBackground(backgroundSurf);
        separatorV.setBackground(backgroundSurf);
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

    public void switchMenuContentfulView(ApplicationMenuViews viewIdf) {
        switchMenuContentfulView(this.getMenuViews().get(viewIdf));
    }

    private void switchMenuContentfulView(MenuContentfulViewWrapper view) {
        if (current != null) {
            current.beforeMenuSwitch();
            current.isCurrentMenuItem(false);
        }
        current = view;
        view.isCurrentMenuItem(true);
        logger.debug(LogMarkers.UI, "Switching to {}", view);
        updateTitle(view.getTitle());
        view.showTOS();
    }

    private void updateTitle(String title) {
        Stage stage = (Stage) contentPlane.getScene().getWindow();
        stage.setTitle(title);
        menubarTitle.setText(title);
    }

    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> _applicationViews;

    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> getMenuViews() {
        if (_applicationViews == null) {
            _applicationViews = new EnumMap<>(ApplicationMenuViews.class);
            _applicationViews.put(ApplicationMenuViews.SEARCH_VIEW, new MenuContentfulViewWrapper<>(new SearchView(), new SearchVM(), "Search", "Search", this));
            _applicationViews.put(ApplicationMenuViews.BROWSER_VIEW, new MenuContentfulViewWrapper<>(new BrowserView(), new ViewModelImpl(), "Event Browser", "Event Browser", this));
            _applicationViews.put(ApplicationMenuViews.DEMO_VIEW, new MenuContentfulViewWrapper<>(new DemoView(), new ViewModelImpl(), "Demo Item 3", "Demo Content Title 3", this));
        }
        return _applicationViews;
    }

    private boolean menuOpen = true;

    private void toggleMenuList(@Nullable ActionEvent e) {
        logger.debug(LogMarkers.UI_EVENT, "Toggle Menu to {}", !menuOpen);
        if (menuOpen) {
            menuVBox.setMinWidth(30D);
            menuVBox.setPrefWidth(30D);
            menuVBox.setMaxWidth(30D);
        } else {
            menuVBox.setMinWidth(150D);
            menuVBox.setPrefWidth(150D);
            menuVBox.setMaxWidth(150D);
        }
        menuOpen = !menuOpen;
    }

    private void setupWindowListener() {
        closeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "EXIT pressed");
            Platform.exit();
        });
        maximizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MAXIMIZE pressed");
            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        });
        minimizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MINIMIZE pressed");
            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
        menubarHBox.setOnMousePressed(e -> {
            originEvent = e;
            Stage stage = (Stage) ((HBox) originEvent.getSource()).getScene().getWindow();
            if (!stage.isMaximized()) {
                xOffset = stage.getX() - originEvent.getScreenX();
                yOffset = stage.getY() - originEvent.getScreenY();
            }
        });
        menubarHBox.setOnDragDetected(e -> {
            Stage stage = (Stage) ((HBox) originEvent.getSource()).getScene().getWindow();
            if (stage.isMaximized()) {
                double widthPercent = originEvent.getSceneX() / stage.getWidth();
                stage.setMaximized(false);
                stage.setX(originEvent.getScreenX() - stage.getWidth() * widthPercent);
                stage.setY(originEvent.getY());
                xOffset = stage.getX() - originEvent.getScreenX();
                yOffset = stage.getY() - originEvent.getScreenY();
            }
        });
        menubarHBox.setOnMouseDragged(e -> {
            Stage stage = (Stage) ((HBox) e.getSource()).getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
    }
}

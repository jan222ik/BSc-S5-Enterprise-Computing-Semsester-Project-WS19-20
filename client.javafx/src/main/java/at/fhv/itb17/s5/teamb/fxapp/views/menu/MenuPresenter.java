package at.fhv.itb17.s5.teamb.fxapp.views.menu;

import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.WindowEventHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ResultVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.RootVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.SearchVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.MenuContentfulViewWrapper;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.BrowserView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.CartView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.search.SearchView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.user.UserView;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.net.URL;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuPresenter implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuPresenter.class);

    @Inject
    private static Style style;
    @Inject
    private SearchService searchService;

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
    private Button userBtn;
    @FXML
    private FontAwesomeIconView userIcon;

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

    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> applicationViews;
    private MenuContentfulViewWrapper current;
    private boolean isMenuDrawerOpen = true;
    private SimpleBooleanProperty userViewOpen = new SimpleBooleanProperty(false);


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
        this.applyStyle();
        this.setupWindowListener();
        glyphHostBtn.setOnAction(this::toggleMenuDrawer);
        menubarHBox.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                this.toggleMenuDrawer(null);
            }
        });
        userBtn.setOnAction(e -> switchMenuContentfulView(ApplicationMenuViews.USER_VIEW));
        userViewOpen.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                userBtn.setTextFill(style.PRIMARY().asPaint());
            } else {
                userBtn.setTextFill(style.ON_BACKGROUND().asPaint());
                userBtn.setBackground(background);
            }
        });
        this.setMenuItems(new LinkedList<>(getMenuViews().values().stream().filter(MenuContentfulViewWrapper::inMenuList).collect(Collectors.toList())));
        Platform.runLater(() -> switchMenuContentfulView(ApplicationMenuViews.SEARCH_VIEW));
    }

    private void applyStyle() {
        style.hoverBtn(closeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundError, style.ON_ERROR().asPaint());
        style.hoverBtn(maximizeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        style.hoverBtn(minimizeBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        style.hoverBtn(userBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint(), userViewOpen);
        style.hoverBtn(glyphHostBtn, background, style.ON_BACKGROUND().asPaint(), backgroundSurf, style.ON_SURFACE().asPaint());
        userIcon.setFill(style.ON_BACKGROUND().asPaint());
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

    private void setMenuItems(@NotNull final LinkedList<MenuContentfulViewWrapper> views) {
        views.forEach(view -> {
            menuVBox.getChildren().add(view.createMenuItemView(() -> {
                logger.debug(LogMarkers.UI_EVENT, "MenuItem clicked");
                this.switchMenuContentfulView(view);
            }, menuVBox.widthProperty()).getView());
            view.isCurrentMenuItem(false);
        });
    }

    public void switchMenuContentfulView(ApplicationMenuViews viewIdf) {
        this.switchMenuContentfulView(this.getMenuViews().get(viewIdf));
    }

    private void switchMenuContentfulView(MenuContentfulViewWrapper view) {
        if (current != null) {
            current.beforeMenuSwitch();
            current.isCurrentMenuItem(false);
        }
        userViewOpen.set(view.equals(getMenuViews().get(ApplicationMenuViews.USER_VIEW)));
        current = view;
        view.isCurrentMenuItem(true);
        logger.debug(LogMarkers.UI, "Switching to {}", view);
        this.updateTitle(view.getTitle());
        view.showTOS();
    }

    private void updateTitle(String title) {
        Stage stage = (Stage) contentPlane.getScene().getWindow();
        stage.setTitle(title);
        menubarTitle.setText(title);
    }

    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> getMenuViews() {
        if (applicationViews == null) {
            applicationViews = new EnumMap<>(ApplicationMenuViews.class);
            RootVM rootVM = new RootVM();
            rootVM.setSearchVM(new SearchVM());
            rootVM.setResultVM(new ResultVM(searchService, rootVM));
            applicationViews.put(ApplicationMenuViews.SEARCH_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new SearchView(), rootVM.getSearchVM(),
                            "Search", "Search", FontAwesomeIcon.SEARCH, true, this)
            );
            applicationViews.put(ApplicationMenuViews.BROWSER_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new BrowserView(), rootVM.getResultVM(), "Event Browser",
                            "Event Browser", FontAwesomeIcon.LIST_UL, true, this)
            );
            applicationViews.put(ApplicationMenuViews.CART_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new CartView(), rootVM.getResultVM(), "Cart",
                            "Cart", FontAwesomeIcon.SHOPPING_CART, true, this)
            );
            applicationViews.put(ApplicationMenuViews.USER_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new UserView(), rootVM, "User",
                            "User", FontAwesomeIcon.ANCHOR, false, this)
            );
        }
        return applicationViews;
    }

    private void toggleMenuDrawer(@Nullable ActionEvent e) {
        logger.debug(LogMarkers.UI_EVENT, "Toggle Menu to {}", !isMenuDrawerOpen);
        if (isMenuDrawerOpen) {
            menuVBox.setMinWidth(30D);
            menuVBox.setPrefWidth(30D);
            menuVBox.setMaxWidth(30D);
        } else {
            menuVBox.setMinWidth(150D);
            menuVBox.setPrefWidth(150D);
            menuVBox.setMaxWidth(150D);
        }
        isMenuDrawerOpen = !isMenuDrawerOpen;
    }

    private void setupWindowListener() {
        WindowEventHelper.closeApplicationImpl(closeBtn);
        WindowEventHelper.maximizeApplicationImpl(maximizeBtn);
        WindowEventHelper.minimizeApplicationImpl(minimizeBtn);
        WindowEventHelper.draggableApplicationWindowImpl(menubarHBox);
    }
}

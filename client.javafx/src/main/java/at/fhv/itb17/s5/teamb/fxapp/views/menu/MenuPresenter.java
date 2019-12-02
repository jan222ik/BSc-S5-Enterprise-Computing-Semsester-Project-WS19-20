package at.fhv.itb17.s5.teamb.fxapp.views.menu;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.WindowEventHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.CartVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.MsgTopicVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ResultVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.RootVM;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.SearchVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.MenuContentfulViewWrapper;
import at.fhv.itb17.s5.teamb.fxapp.views.content.browser.BrowserView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.CartView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon.MsgConView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.msgprod.MsgProdView;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MenuPresenter implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuPresenter.class);

    @Inject
    private static Style style;
    @Inject
    private SearchService searchService;
    @Inject
    private BookingService bookingService;
    @Inject
    private MsgTopicService msgTopicService;


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

    private static Background background;
    private static Background backgroundError;
    private static Background backgroundSurf;

    @SuppressWarnings("rawtypes")
    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> applicationViews;
    @SuppressWarnings("rawtypes")
    private MenuContentfulViewWrapper current;
    private boolean isMenuDrawerOpen = true;
    private SimpleBooleanProperty userViewOpen = new SimpleBooleanProperty(false);
    private Runnable logoutCallback = null;
    private Consumer<String> username;


    @Override
    @SuppressWarnings({"squid:S2696", "squid:S5411"})
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
        userBtn.setOnAction(e -> switchMenuContentfulView(ApplicationMenuViews.USER_VIEW, true));
        userViewOpen.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                userBtn.setTextFill(style.PRIMARY().asPaint());
            } else {
                userBtn.setTextFill(style.ON_BACKGROUND().asPaint());
                userBtn.setBackground(background);
            }
        });
        this.setMenuItems(new LinkedList<>(getMenuViews().values().stream().filter(MenuContentfulViewWrapper::inMenuList).collect(Collectors.toList())));
        Platform.runLater(() -> switchMenuContentfulView(ApplicationMenuViews.SEARCH_VIEW, true));
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

    private void setMenuItems(@SuppressWarnings("rawtypes") @NotNull final LinkedList<MenuContentfulViewWrapper> views) {
        views.forEach(view -> {
            menuVBox.getChildren().add(view.createMenuItemView(() -> {
                logger.debug(LogMarkers.UI_EVENT, "MenuItem clicked");
                this.switchMenuContentfulViewView(view, true);
            }, menuVBox.widthProperty()).getView());
            view.isCurrentMenuItem(false);
        });
    }

    public void switchMenuContentfulView(ApplicationMenuViews viewIdf, boolean pop2root) {
        this.switchMenuContentfulViewView(this.getMenuViews().get(viewIdf), pop2root);
    }

    private void switchMenuContentfulViewView(@SuppressWarnings("rawtypes") MenuContentfulViewWrapper view, boolean pop2root) {
        if (current != null) {
            current.beforeMenuSwitch();
            current.isCurrentMenuItem(false);
        }
        userViewOpen.set(view.equals(getMenuViews().get(ApplicationMenuViews.USER_VIEW)));
        current = view;
        view.isCurrentMenuItem(true);
        logger.debug(LogMarkers.UI, "Switching to {}", view);
        this.updateTitle(view.getTitle());
        if (pop2root) {
            view.popToRoot();
        }
        view.showTOS();
    }

    private void updateTitle(String title) {
        Stage stage = (Stage) contentPlane.getScene().getWindow();
        stage.setTitle(title);
        menubarTitle.setText(title);
    }

    @SuppressWarnings("rawtypes")
    private EnumMap<ApplicationMenuViews, MenuContentfulViewWrapper> getMenuViews() {
        if (applicationViews == null) {
            applicationViews = new EnumMap<>(ApplicationMenuViews.class);
            RootVM rootVM = new RootVM();
            username = rootVM::setUsername;
            rootVM.setSearchVM(new SearchVM());
            rootVM.setCartVM(new CartVM(bookingService));
            rootVM.setResultVM(new ResultVM(searchService, rootVM));
            rootVM.setMsgTopicVM(new MsgTopicVM(msgTopicService));
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
                            new CartView(), rootVM.getCartVM(), "Cart",
                            "Cart", FontAwesomeIcon.SHOPPING_CART, true, this)
            );
            applicationViews.put(ApplicationMenuViews.USER_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new UserView(), rootVM, "User",
                            "User", FontAwesomeIcon.ANCHOR, false, this)
            );
            userBtn.setText(rootVM.getUsername());
            applicationViews.put(ApplicationMenuViews.MSG_PROD_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new MsgProdView(), rootVM.getMsgTopicVM(), "Message Producer",
                            "MSG. Prod.", FontAwesomeIcon.ENVELOPE, false, this)
            );
            applicationViews.put(ApplicationMenuViews.MSG_CONSUMER_VIEW,
                    new MenuContentfulViewWrapper<>(
                            new MsgConView(), rootVM.getMsgTopicVM(), "Message Consumer",
                            "MSG. Consumer", FontAwesomeIcon.ENVELOPE, true, this)
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
        WindowEventHelper.closeApplicationImpl(closeBtn, () -> {
            try {
                this.logout();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        WindowEventHelper.maximizeApplicationImpl(maximizeBtn);
        WindowEventHelper.minimizeApplicationImpl(minimizeBtn);
        WindowEventHelper.draggableApplicationWindowImpl(menubarHBox);
    }

    public void setLogoutCallback(Runnable callbackLogout) {
        this.logoutCallback = callbackLogout;
    }

    public void logout() throws IllegalAccessException {
        if (logoutCallback == null) throw new IllegalAccessException("Apply Setter before Invocation");
        bookingService.logout();
        if (msgTopicService != null) {
            msgTopicService.logout();
        }
        logoutCallback.run();
    }

    public void setUsername(String name) {
        username.accept(name);
        userBtn.setText(name);
    }
}

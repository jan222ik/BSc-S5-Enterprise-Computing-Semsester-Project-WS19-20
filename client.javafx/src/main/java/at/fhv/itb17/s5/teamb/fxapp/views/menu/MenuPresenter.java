package at.fhv.itb17.s5.teamb.fxapp.views.menu;

import at.fhv.itb17.s5.teamb.fxapp.views.MenuContentfulView;
import at.fhv.itb17.s5.teamb.fxapp.views.demo.DemoView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MenuPresenter implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuPresenter.class);

    @FXML private
    AnchorPane contentPlane;
    @FXML
    private VBox menuVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("Init {}", MenuPresenter.class.getName());
        LinkedList<MenuContentfulView> menuContentfulViews = getMenuViews();
        setMenuItems(menuContentfulViews);
    }

    private void setContentView(Parent viewRootElement) {
        contentPlane.getChildren().clear();
        contentPlane.getChildren().add(viewRootElement);
    }

    private void setMenuItems(final LinkedList<MenuContentfulView> views) {
        views.forEach(e -> menuVBox.getChildren().add(e.createMenuItemView(() -> switchMenuContentfulView(e)).getView()));
    }

    private void switchMenuContentfulView(MenuContentfulView view) {
        logger.debug("Switching to {}", view);
    }

    private LinkedList<MenuContentfulView> getMenuViews() {
        MenuContentfulView item1 = new MenuContentfulView(new DemoView(), "Demo Item 1", "Demo Content Title 1");
        MenuContentfulView item2 = new MenuContentfulView(new DemoView(), "Demo Item 2", "Demo Content Title 2");
        MenuContentfulView item3 = new MenuContentfulView(new DemoView(), "Demo Item 3", "Demo Content Title 3");
        return new LinkedList<>(Arrays.asList(item1, item2, item3));
    }
}

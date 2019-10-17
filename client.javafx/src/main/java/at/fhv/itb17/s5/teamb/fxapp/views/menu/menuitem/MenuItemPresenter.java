package at.fhv.itb17.s5.teamb.fxapp.views.menu.menuitem;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuItemPresenter implements Initializable {

    @Inject
    private Style style;

    private Background selected;
    private Paint selectedPaint;
    private Background unselected;
    private Paint unselectedPaint;



    @FXML
    private Button menuItem;

    private Runnable onAction;

    public void setOnClickedAction(Runnable onClickAction) {
        this.onAction = onClickAction;
    }

    public void setDisplayName(String menuName) {
        menuItem.setText(menuName);
    }

    @FXML
    private void onAction(ActionEvent ignored) {
        if (onAction != null) {
            onAction.run();
        }
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            menuItem.setBackground(selected);
            menuItem.setTextFill(selectedPaint);
        } else {
            menuItem.setBackground(unselected);
            menuItem.setTextFill(unselectedPaint);
        }
    }

    public void setParentWidthProperty(ReadOnlyDoubleProperty parentWidthProperty) {
        parentWidthProperty.addListener((observable, oldValue, newValue) -> menuItem.setPrefWidth((Double) newValue - 2.0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selected = new Background(new BackgroundFill(style.PRIMARY_PAINT(), null, null));
        selectedPaint = style.ON_PRIMARY_PAINT();
        unselected = new Background(new BackgroundFill(style.SURFACE_PAINT(), null, null));
        unselectedPaint = style.ON_SURFACE_PAINT();
    }
}

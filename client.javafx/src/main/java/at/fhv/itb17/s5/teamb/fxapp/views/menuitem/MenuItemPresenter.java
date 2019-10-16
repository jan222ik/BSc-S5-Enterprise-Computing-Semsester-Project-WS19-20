package at.fhv.itb17.s5.teamb.fxapp.views.menuitem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuItemPresenter {

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
}

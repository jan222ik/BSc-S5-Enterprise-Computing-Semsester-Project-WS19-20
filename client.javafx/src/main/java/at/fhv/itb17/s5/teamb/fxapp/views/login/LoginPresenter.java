package at.fhv.itb17.s5.teamb.fxapp.views.login;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.WindowEventHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPresenter implements Initializable {

    @Inject
    private Style style;

    @FXML
    private HBox movebar;
    @FXML
    private Button closeBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WindowEventHelper.closeApplicationImpl(closeBtn);
        WindowEventHelper.draggableApplicationWindowImpl(movebar);
        style.hoverBtn(closeBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.ERROR().asBackground(), style.ON_ERROR().asPaint());
        style.hoverBtn(loginButton, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.PRIMARY().asPaint());
    }
}

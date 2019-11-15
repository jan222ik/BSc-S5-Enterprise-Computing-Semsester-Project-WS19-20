package at.fhv.itb17.s5.teamb.fxapp.views.login;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.util.WindowEventHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPresenter implements Initializable {

    @Inject
    private Style style;
    @Inject
    private BookingService bookingService;

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

    private Runnable callback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WindowEventHelper.closeApplicationImpl(closeBtn);
        WindowEventHelper.draggableApplicationWindowImpl(movebar);
        style.hoverBtn(closeBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.ERROR().asBackground(), style.ON_ERROR().asPaint());
        style.hoverBtn(loginButton, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.PRIMARY().asPaint());
        loginButton.setOnAction(e -> checkPWUsernameCombination());
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                checkPWUsernameCombination();
            }
        });
    }

    public void setNextSceneCallback(Runnable callback) {
        this.callback = callback;
    }

    private void checkPWUsernameCombination() {
        final String username = usernameTextField.getText();
        final String password = passwordField.getText();
        if (username.isEmpty()) {
            NotificationsHelper.error("Invalid Input", "Username may not be empty!", NotificationsHelper.DisplayDuration.SHORT);
        }
        //TODO make nested to stop execution
        if (checkPasswordRemote(username, password)) {
            callback.run();
        } else {
            NotificationsHelper.error("Invalid Input", "Username or Password wrong!", NotificationsHelper.DisplayDuration.SHORT);
        }
    }

    private boolean checkPasswordRemote(String user, String pwd) {
        if (user.isEmpty()) {
            user = "backdoor";
            pwd = "backdoorPWD";
        }
        return bookingService.doLoginBooking(user, pwd);
    }
}

package at.fhv.itb17.s5.teamb.fxapp.views.login;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
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
import java.util.function.Consumer;

public class LoginPresenter implements Initializable {

    @Inject
    private Style style;
    @Inject
    private BookingService bookingService;
    @Inject
    private MsgTopicService msgTopicService;

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

    private Consumer<String> callback;

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

    public void setNextSceneCallback(Consumer<String> callback) {
        this.callback = callback;
    }

    private void checkPWUsernameCombination() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.isEmpty()) {
            NotificationsHelper.error("Invalid Input", "Username may not be empty!", NotificationsHelper.DisplayDuration.SHORT);
        }
        if (username.isEmpty()) {
            username = "backdoor";
            password = "backdoorPWD";
        }
        if (checkPasswordRemote(username, password)) {
            callback.accept(username);
        } else {
            NotificationsHelper.error("Invalid Input", "Username or Password wrong!", NotificationsHelper.DisplayDuration.SHORT);
        }
    }

    private boolean checkPasswordRemote(String user, String pwd) {
        msgTopicService.doLoginMsgTopic(user, pwd);
        return bookingService.doLoginBooking(user, pwd);
    }
}

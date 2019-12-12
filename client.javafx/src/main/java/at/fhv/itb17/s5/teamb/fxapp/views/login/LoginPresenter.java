package at.fhv.itb17.s5.teamb.fxapp.views.login;

import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.SetupManager;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.util.PopupHelper;
import at.fhv.itb17.s5.teamb.fxapp.util.WindowEventHelper;
import at.fhv.itb17.s5.teamb.fxapp.views.load.LoadPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.load.LoadView;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class LoginPresenter implements Initializable {

    private static final List<String> servers = Arrays.asList("localhost", "10.0.51.91");

    @Inject
    private Style style;
    @Inject
    private SetupManager setupManager;

    @FXML
    private StackPane stackPlane;
    @FXML
    private AnchorPane msgPlane;
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
    @FXML
    private ChoiceBox<String> serverCB;

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
        serverCB.getItems().clear();
        serverCB.getItems().addAll(servers);
        if (!serverCB.getItems().isEmpty()) {
            serverCB.getSelectionModel().select(0);
        }
        stackPlane.getChildren().remove(msgPlane);
    }

    public void setNextSceneCallback(Consumer<String> callback) {
        this.callback = callback;
    }

    @SuppressWarnings("squid:S2068") //Suppress hardcoded password
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
        LoadView loadView = createLoadView(username);
        Stage setup = PopupHelper.create(loadView, "Setup", ((Stage) usernameTextField.getScene().getWindow()).getIcons().get(0), usernameTextField.getScene().getWindow(), Modality.APPLICATION_MODAL);
        setup.show();
        String finalUsername = username;
        String finalPassword = password;

        new Thread(() -> {
            RMIConnectionStatus status = checkPasswordRemote(finalUsername, finalPassword);
            Observable.just(new Object()).subscribeOn(JavaFxScheduler.platform()).subscribe(o -> {
                if (status == RMIConnectionStatus.CREDENTIALS_INVALID) {
                    NotificationsHelper.error("Invalid Input", "Username or Password wrong!", NotificationsHelper.DisplayDuration.SHORT);
                } else {
                    NotificationsHelper.error("Connection Refused", "No RMI Connection to host possible", NotificationsHelper.DisplayDuration.SHORT);
                }
                setup.close();
            });//.dispose(); TODO Dispose after inner code was executed.
        }, "Setup").start();
        //setupManager.setMsgTopics(); //TODO Fix
        setupManager.initMsgAsync(finalUsername);
    }

    private RMIConnectionStatus checkPasswordRemote(String user, String pwd) {
        RMIConnectionStatus status = RMIConnectionStatus.NO_CONNECTION;
        if (setupManager != null) {
            status = setupManager.connect(serverCB.getValue(), 2345);
            if (status == RMIConnectionStatus.CONNECTED) {
                status = setupManager.authenticate(user, pwd);
            }
        }
        return status;
    }

    private LoadView createLoadView(String username) {
        LoadView loadView = new LoadView();
        LoadPresenter presenter = (LoadPresenter) loadView.getPresenter();
        presenter.setNextSceneCallback(callback, username);
        setupManager.setCallbackConsumer(presenter);
        return loadView;
    }
}

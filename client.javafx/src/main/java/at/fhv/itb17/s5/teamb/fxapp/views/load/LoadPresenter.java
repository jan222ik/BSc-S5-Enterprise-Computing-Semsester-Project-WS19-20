package at.fhv.itb17.s5.teamb.fxapp.views.load;

import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.SetupCallback;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class LoadPresenter implements SetupCallback {

    @FXML
    private Text outText;

    String currentText = "Setup:\n";

    private Consumer<String> callback;
    private String callbackValue;

    public void setNextSceneCallback(Consumer<String> callback, String callbackValue) {
        this.callback = callback;
        this.callbackValue = callbackValue;
    }

    @Override
    public void onNextSetup(String name, int currentStep, int totalSteps) {
        currentText += "Setup Step " + currentStep + " of " + totalSteps + ": " + name + "\n";
        outText.setText(currentText);
    }

    @Override
    public void setupFinished() {
        callback.accept(callbackValue);
    }
}

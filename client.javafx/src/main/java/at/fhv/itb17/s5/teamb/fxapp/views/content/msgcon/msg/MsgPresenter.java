package at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon.msg;

import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

public class MsgPresenter {

    @FXML
    private Label topicL;
    @FXML
    private Label headerL;
    @FXML
    private Label bodyL;
    @FXML
    private Button ackBtn;

    public void displayMsg(@NotNull MsgWrapper msg, @NotNull Runnable onAck) {
        ackBtn.setOnAction(e -> onAck.run());
        topicL.setText(msg.getTopicName());
        headerL.setText(msg.getHeader());
        bodyL.setText(msg.getMsgText());
    }
}

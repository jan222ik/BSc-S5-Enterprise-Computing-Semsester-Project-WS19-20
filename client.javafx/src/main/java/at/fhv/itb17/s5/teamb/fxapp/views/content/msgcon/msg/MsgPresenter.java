package at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon.msg;

import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MsgPresenter {

    @Inject
    private Style style;

    @FXML
    private Label topicL;
    @FXML
    private Label headerL;
    @FXML
    private Label bodyL;
    @FXML
    private Button ackBtn;

    public void displayMsg(@NotNull MsgWrapper msg, @NotNull Runnable onAck) {
        style.hoverBtn(ackBtn, style.SURFACE().asBackground(), style.ON_SURFACE().asPaint(), style.SURFACE().asBackground(), style.PRIMARY().asPaint());
        ackBtn.setOnAction(e -> onAck.run());
        topicL.setText(msg.getTopicName());
        headerL.setText(msg.getHeader());
        bodyL.setText(msg.getMsgText());
    }
}

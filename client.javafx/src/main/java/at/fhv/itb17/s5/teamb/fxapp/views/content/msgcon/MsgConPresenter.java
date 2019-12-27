package at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon;

import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.fxapp.data.msg.MsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.msg.MsgConsumer;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.MsgTopicVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon.msg.MsgPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon.msg.MsgView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MsgConPresenter implements ContentfulViewLifeCycle<MsgTopicVM> {

    @FXML
    private JFXListView<Parent> notificationLV;
    @FXML
    private VBox topicFilterList;
    @FXML
    private VBox rootPlane;
    @FXML
    private VBox msgprodVbox;
    @FXML
    private Button msgprodBtn;

    //private MsgAsyncServiceImpl consumer = new MsgAsyncServiceImpl();
   //private static final String VM_LOCALHOST = "vm://localhost";
    private static final String TCP = "tcp://localhost:61616";
    @Override
    public void onCreate(MsgTopicVM viewModel, NavigationStackActions<MsgTopicVM> navActions) {
        boolean publish = viewModel.mayPublish();
        if (!publish) {
            rootPlane.getChildren().remove(msgprodVbox);
        } else {
            msgprodBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.MSG_PROD_VIEW, true));
        }
    }
    /*
    this.topicName = topicName;
        this.msgText = msgText;
        this.textMessage = textMessage;
        this.timestamp = timestamp;
        this.ack = ack;
        this.header = header;
     */

    @Override
    public void onReturned(MsgTopicVM viewModel) {
        //TODO Load & display Messages and the existing topics
        notificationLV.getItems().clear();
        List<MsgWrapper> msgs = viewModel.getAllMsgs();
        List<Parent> collect = msgs.stream().map(msg -> createView(msg, () -> {
            try {
                viewModel.ack(msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }).getView()).collect(Collectors.toList());
        notificationLV.getItems().addAll(collect);
    }

    private MsgView createView(MsgWrapper msg, Runnable runnable) {
        MsgView msgView = new MsgView();
        MsgPresenter pres = (MsgPresenter) msgView.getPresenter();
        pres.displayMsg(msg, runnable);
        return msgView;
    }
}

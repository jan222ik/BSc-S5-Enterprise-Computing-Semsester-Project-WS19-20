package at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import javax.jms.JMSException;
import java.util.HashMap;
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
    @FXML
    private Button ackAll;
    private HashMap<String, ToggleButton> toggles = new HashMap<>();

    @Override
    public void onCreate(MsgTopicVM viewModel, NavigationStackActions<MsgTopicVM> navActions) {
        boolean publish = viewModel.mayPublish();
        if (!publish) {
            rootPlane.getChildren().remove(msgprodVbox);
        } else {
            msgprodBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.MSG_PROD_VIEW, true));
        }
        ackAll.setOnAction(e -> {
            List<MsgWrapper> allMsgs = viewModel.getAllMsgs();
            if (!allMsgs.isEmpty()) {
                for (MsgWrapper allMsg : allMsgs) {
                    try {
                        allMsg.getTextMessage().acknowledge();
                    } catch (JMSException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        List<MsgTopicDTO> allTopics = viewModel.getSubscribedTopics();
        for (MsgTopicDTO topic : allTopics) {
            ToggleButton toggleButton = new ToggleButton(topic.getName());
            toggleButton.setOnAction(e -> onReturned(viewModel));
            topicFilterList.getChildren().add(toggleButton);
            toggles.put(topic.getName(), toggleButton);
        }
    }

    @Override
    public void onReturned(MsgTopicVM viewModel) {
        notificationLV.getItems().clear();
        List<MsgWrapper> msgs = viewModel.getAllMsgs();
        List<Parent> collect = msgs.stream().filter(msg -> !(toggles.get(msg.getTopicName()).isSelected())).map(msg -> createView(msg, () -> {
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

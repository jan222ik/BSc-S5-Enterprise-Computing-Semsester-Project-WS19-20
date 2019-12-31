package at.fhv.itb17.s5.teamb.fxapp.views.content.msgprod.msgconrss;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.MsgTopicVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MsgProdRssPresenter implements ContentfulViewLifeCycle<MsgTopicVM> {

    @FXML
    private ComboBox<String> feedsCB;
    @FXML
    private Button addBtn;
    @FXML
    private ComboBox<String> topicCB;
    private Map<String, MsgTopicDTO> topics;


    @Override
    public void onCreate(MsgTopicVM viewModel, NavigationStackActions<MsgTopicVM> navActions) {
        topics = new HashMap<>();
        addBtn.setOnAction(e -> {
            String feed = feedsCB.getValue();
            if (topicCB.getValue() != null && feed != null) {
                MsgTopicDTO msgTopicDTO = topics.get(topicCB.getValue());
                boolean b = viewModel.publishFromFeed(msgTopicDTO, feed);
                if (b) {
                    navActions.changeToMenuItem(ApplicationMenuViews.MSG_CONSUMER_VIEW, true);
                } else {
                    NotificationsHelper.error("Error" ,"The feed could not be read");
                }
            } else {
                NotificationsHelper.error("Input invalid", "Please select a topic");
            }
        });
    }

    @Override
    public void onReturned(MsgTopicVM viewModel) {
        List<MsgTopicDTO> allTopics = viewModel.getAllTopics();
        topicCB.getItems().clear();
        topicCB.getItems().addAll(allTopics.stream().filter(topic -> !(topic.getName().equals("TEST"))).map(e -> {
            topics.put(e.getName(), e);
            return e.getName();
        }).collect(Collectors.toList()));
        feedsCB.getItems().clear();
        feedsCB.getItems().addAll(viewModel.getRSSFeedURLs());
    }
}

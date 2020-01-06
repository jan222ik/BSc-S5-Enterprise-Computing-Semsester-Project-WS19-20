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
import javafx.scene.control.TextField;

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
    @FXML
    private TextField rssInput;
    private Map<String, MsgTopicDTO> topics;


    @Override
    public void onCreate(MsgTopicVM viewModel, NavigationStackActions<MsgTopicVM> navActions) {
        topics = new HashMap<>();
        rssInput.setMaxWidth(150);
        addBtn.setOnAction(e -> {
            String feed = feedsCB.getValue();
            String customInput = rssInput.getText();
            if (topicCB.getValue() != null) {
                MsgTopicDTO msgTopicDTO = topics.get(topicCB.getValue());
                boolean couldReadFeed = false;
                if (feed != null) {
                    couldReadFeed = viewModel.publishFromFeed(msgTopicDTO, feed);
                }
                else if (customInput != null) {
                    couldReadFeed = viewModel.publishFromFeed(msgTopicDTO, customInput);
                }
                if (couldReadFeed) {
                    navActions.changeToMenuItem(ApplicationMenuViews.MSG_CONSUMER_VIEW, true);
                } else {
                    NotificationsHelper.error("Error", "The feed could not be read");
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

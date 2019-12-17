package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;

import java.util.LinkedList;
import java.util.List;

@Deprecated
public class MockMsgTopicServiceImpl implements at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService {
    @Override
    public RMIConnectionStatus doLoginMsgTopic(String username, String password) {
        return RMIConnectionStatus.CONNECTED;
    }

    @Override
    public void logout() {
        //May be empty because no teardown needed
    }

    @Override
    @SuppressWarnings("squid:S1168") //Null return
    public List<MsgTopicDTO> getAll() {
        return null;
    }

    @Override
    public boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) {
        return false;
    }

    @Override
    public boolean mayPublish() {
        return false;
    }

    @Override
    @SuppressWarnings("squid:S1168") //Null return
    public List<MsgTopicDTO> getSubscribedTopics() {
        return null;
    }

    @Override
    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) {
        return true;
    }

    @Override
    public List<String> getRSSFeedURLs() {
        return new LinkedList<>();
    }
}

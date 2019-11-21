package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;

import java.util.List;

public class MockMsgTopicServiceImpl implements at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService {
    @Override
    public boolean doLoginMsgTopic(String username, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
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
}

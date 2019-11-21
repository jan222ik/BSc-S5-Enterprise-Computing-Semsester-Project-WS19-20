package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;

import java.util.List;

public interface MsgTopicService {
    boolean doLoginMsgTopic(String username, String password);

    void logout();

    List<MsgTopicDTO> getAll();

    boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body);

    boolean mayPublish();
}

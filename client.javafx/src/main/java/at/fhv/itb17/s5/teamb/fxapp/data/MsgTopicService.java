package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIConnectionStatus;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.util.List;

public interface MsgTopicService {
    RMIConnectionStatus doLoginMsgTopic(String username, String password);

    void logout();

    List<MsgTopicDTO> getAll();

    boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body);

    boolean mayPublish();

    List<MsgTopic> getSubscribedTopics();

    List<MsgWrapper> getAllMessages();
}

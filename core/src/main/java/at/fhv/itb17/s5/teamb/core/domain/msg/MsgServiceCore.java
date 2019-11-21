package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import java.util.List;

public interface MsgServiceCore {
    //List<MsgTopic> getSubscribedTopics(); TODO is needed ?
    List<MsgTopic> getAllTopics();
    boolean createMessage(MsgTopic topic, String messageHeader, String messageBody);

}

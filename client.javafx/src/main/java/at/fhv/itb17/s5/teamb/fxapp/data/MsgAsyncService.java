package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;

import javax.jms.JMSException;
import java.util.List;

public interface MsgAsyncService {
    void init(String brokerUrl, String clientID) throws JMSException;

    List<MsgWrapper> getAllMsgs();

    void setPresenter(ApplicationMain applicationMain);

    void close() throws JMSException;

    void setTopics(List<MsgTopicDTO> topics);
}

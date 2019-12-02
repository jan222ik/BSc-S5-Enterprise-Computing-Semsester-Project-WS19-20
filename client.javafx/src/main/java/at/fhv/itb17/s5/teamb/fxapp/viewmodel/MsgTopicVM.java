package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class MsgTopicVM implements ViewModel {

    private static final Logger logger = LogManager.getLogger(MsgTopicVM.class);

    private MsgTopicService msgTopicService;

    public MsgTopicVM(MsgTopicService msgTopicService) {
        this.msgTopicService = msgTopicService;
    }

    public List<MsgTopicDTO> getAllTopics() {
        return msgTopicService.getAll();
    }

    public boolean publishMsg(MsgTopicDTO msgTopicDTO, String header, String body) {
        return msgTopicService.publishMsg(msgTopicDTO, header, body);
    }

    public boolean mayPublish() {
        return msgTopicService.mayPublish();
    }

    public void ack(MsgWrapper msg) {
        String s = msg.toString();
        logger.debug("acked {}", s); //TODO impl
    }

    public List<MsgWrapper> getAllMsgs() {
        LinkedList<MsgWrapper> msgWrappers = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            msgWrappers.add(new MsgWrapper("Topic " + i, "msg " + i, null, LocalDateTime.now(), false, "header " + i));
        }
        return new LinkedList<>(msgWrappers); //TODO impl*/
    }

    public List<MsgTopic> getSubscribedTopics() {
        return msgTopicService.getSubscribedTopics();
    }

}

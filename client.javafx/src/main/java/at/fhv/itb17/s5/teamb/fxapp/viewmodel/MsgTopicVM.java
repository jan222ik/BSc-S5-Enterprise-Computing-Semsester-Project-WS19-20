package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.JMSException;
import java.util.List;

public class MsgTopicVM implements ViewModel {

    private static final Logger logger = LogManager.getLogger(MsgTopicVM.class);

    private MsgTopicService msgTopicService;
    private MsgAsyncService msgAsyncService;

    public MsgTopicVM(MsgTopicService msgTopicService, MsgAsyncService msgAsyncService) {
        this.msgTopicService = msgTopicService;
        this.msgAsyncService = msgAsyncService;
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

    public void ack(MsgWrapper msg) throws JMSException {
        String s = msg.toString();
        logger.debug("acking {}", s);
        msg.getTextMessage().acknowledge();
    }

    public List<MsgWrapper> getAllMsgs() {
        return msgAsyncService.getAllMsgs();
    }

    public List<MsgTopic> getSubscribedTopics() {
        return msgTopicService.getSubscribedTopics();
    }

    public boolean publishFromFeed(MsgTopicDTO msgTopicDTO, String feedURL) {
        return msgTopicService.publishFromFeed(msgTopicDTO, feedURL);
    }

    public List<String> getRSSFeedURLs() {
        return msgTopicService.getRSSFeedURLs();
    }
}

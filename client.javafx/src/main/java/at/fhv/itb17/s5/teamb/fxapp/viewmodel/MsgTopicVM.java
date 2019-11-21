package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;

import java.util.List;

public class MsgTopicVM implements ViewModel {

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
}

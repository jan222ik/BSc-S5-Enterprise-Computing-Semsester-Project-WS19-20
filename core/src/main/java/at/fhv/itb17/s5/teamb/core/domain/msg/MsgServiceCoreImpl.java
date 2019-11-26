package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;

import java.util.List;

public class MsgServiceCoreImpl implements MsgServiceCore {

    private MsgRepository msgRepository;

    public MsgServiceCoreImpl(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }

    @Override
    public List<MsgTopic> getAllTopics() {
        return msgRepository.getAllTopics();
    }

    @Override
    public boolean createMessage(MsgTopic topic, String messageHeader, String messageBody) {
        MsgProducer prod = new MsgProducer();
        return prod.createMessagePub(messageHeader, messageBody, topic); //TODO proper impl
    }

}

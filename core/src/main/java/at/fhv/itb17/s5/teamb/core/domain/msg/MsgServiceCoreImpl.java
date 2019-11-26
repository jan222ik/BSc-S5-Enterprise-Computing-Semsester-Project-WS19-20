package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.List;

public class MsgServiceCoreImpl implements MsgServiceCore {

    private MsgRepository msgRepository;
    private MsgProducer msgProducer;
    private MsgConsumer msgConsumer;

    public MsgServiceCoreImpl(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
        this.msgProducer = new MsgProducer(msgRepository);
        this.msgConsumer = new MsgConsumer(); //TODO get Client into here somehow -> if necessary
    }

    @Override
    public List<MsgTopic> getAllTopics() {
        return msgRepository.getAllTopics();
    }

    @Override
    public boolean createMessage(MsgTopic topic, String messageHeader, String messageBody) {
        boolean created = false;
        try {
            msgProducer.init("vm://localhost");
            created = msgProducer.createMessagePub(messageHeader, messageBody, topic);
            msgProducer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public List<TextMessage> getAllMessages() {
        try {
            msgProducer.sendCreatedMessages();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return msgConsumer.getMessages();
    }

}

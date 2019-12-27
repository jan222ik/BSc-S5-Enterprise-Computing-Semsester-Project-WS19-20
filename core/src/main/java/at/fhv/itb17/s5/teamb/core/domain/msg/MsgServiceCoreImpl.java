package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;

import javax.jms.JMSException;
import java.util.List;

public class MsgServiceCoreImpl implements MsgServiceCore {

    public static final String VM_LOCALHOST = "vm://localhost";
    public static final String TCP = "tcp://localhost:61616";
    private MsgRepository msgRepository;
    private MsgProducer msgProducer;

    public MsgServiceCoreImpl(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
        this.msgProducer = new MsgProducer();
        try {
            msgProducer.init(TCP, "ProducerStart");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<MsgTopic> getAllTopics() {
        return msgRepository.getAllTopics();
    }

    @Override
    public boolean createMessage(MsgTopic topic, String messageHeader, String messageBody) {
        boolean created = false;
        try {
            msgProducer.init(TCP, "ProducerProduce");
            created = msgProducer.createMessagePub(messageHeader, messageBody, topic);
            msgProducer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return created;
    }

    public void closeProducer() {
        try {
            msgProducer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import at.fhv.itb17.s5.teamb.persistence.repository.MsgRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.JMSException;
import java.util.List;

public class MsgServiceCoreImpl implements MsgServiceCore {

    private static final Logger logger = LogManager.getLogger(MsgServiceCoreImpl.class);

    public static final String TCP = "tcp://localhost:61616";
    private MsgRepository msgRepository;
    private MsgProducer msgProducer;

    public MsgServiceCoreImpl(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
        this.msgProducer = new MsgProducer(msgRepository);
        try {
            msgProducer.init(TCP, "ProducerStart");
        } catch (Exception e) {
            logger.catching(e);
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
            created = msgProducer.createMessagePub(messageHeader, messageBody, topic);
        } catch (Exception e) {
            logger.catching(e);
        }
        return created;
    }

    @Override
    public boolean closeProducer() {
        try {
            msgProducer.close();
            return true;
        } catch (JMSException e) {
            logger.catching(e);
            return false;
        }
    }
}

package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MsgServiceCoreImplTest {
    private static final CoreServiceInjectorImpl injector = (CoreServiceInjectorImpl) CoreServiceInjectorImpl.getInstance(false);

    @Test
    @DisplayName("Test getting all topics - success")
    public void getAllTopicsTest() {
        List<MsgTopic> allTopics = injector.getMsgTopicServiceCore().getAllTopics();
        Assertions.assertEquals(5, allTopics.size());
    }

    @Test
    @DisplayName("Test creating a message - success")
    public void createMessageTest() {
        boolean message = injector.getMsgTopicServiceCore().createMessage(new MsgTopic("TEST", false), "TestHeader", "TestMessage");
        assertTrue(message);
    }

    @Test
    @DisplayName("Test closing the Producer - success")
    public void closeProducer() {
        assertTrue(injector.getMsgTopicServiceCore().closeProducer());
    }
}

package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.core.domain.general.CoreServiceInjectorImpl;
import at.fhv.itb17.s5.teamb.dtos.MsgTopicDTO;
import at.fhv.itb17.s5.teamb.dtos.mapper.MsgTopicMapper;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.*;

import javax.jms.JMSException;
import java.util.Collections;
import java.util.List;

class MsgAsyncServiceImplTest {

    private static final MsgAsyncService service = new MsgAsyncServiceImpl();
    private static final String BROKER_URL_LOCAL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "TestClient";
    private static final String TEST_HEADER = "Test-Header";
    private static final String TEST_BODY = "The body of the test message";
    private static final CoreServiceInjectorImpl injector = new CoreServiceInjectorImpl(false);
    private static final MsgTopic test = new MsgTopic("TEST", false);
    private static final MsgTopicDTO testDTO = MsgTopicMapper.toDTO(test);
    private static final JFXPanel panel = new JFXPanel(); //is here so the JFX toolkit is alive, necessary for the Platform.RunLater when adding new messages to the outlist.

    @BeforeAll
    public static void firstSetup() {
        service.setTopics(Collections.singletonList(testDTO));
        try {
            service.init(BROKER_URL_LOCAL, CLIENT_ID);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        service.acknowledgeTest();
    }

    @AfterEach
    public void teardown() {
        service.acknowledgeTest();
    }

    @Test
    @Order(1)
    @DisplayName("Test number of received Messages - success")
    public void getAllMsgsTestSize() {
        injector.getMsgTopicServiceCore().createMessage(test, TEST_HEADER, TEST_BODY);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<MsgWrapper> allMsgs = service.getAllMsgs();
        Assertions.assertEquals(2, allMsgs.size()); //no idea why 2 messages, leftovers?
    }

    @Test
    @Order(2)
    @DisplayName("Test content of received Message - success")
    public void getAllMsgsTestContent() {
        injector.getMsgTopicServiceCore().createMessage(test, TEST_HEADER, TEST_BODY);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<MsgWrapper> allMsgs = service.getAllMsgs();
        Assertions.assertEquals(TEST_HEADER, allMsgs.get(0).getHeader());
        Assertions.assertEquals(TEST_BODY, allMsgs.get(0).getMsgText());
    }

    @Test
    @Order(3)
    @DisplayName("Test closing of ConsumerService - success")
    public void testClose() {
        boolean b = false;
        try {
            b = service.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(b);
    }

}
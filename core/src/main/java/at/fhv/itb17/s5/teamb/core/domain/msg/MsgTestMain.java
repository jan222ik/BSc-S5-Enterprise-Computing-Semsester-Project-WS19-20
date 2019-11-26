package at.fhv.itb17.s5.teamb.core.domain.msg;

import javax.jms.JMSException;

public class MsgTestMain {
    private static final String VM_LOCALHOST = "vm://localhost";

    public static void main(String[] args) {
        MsgProducer prod = new MsgProducer();
        MsgConsumer cons = new MsgConsumer();
        MsgConsumer consumer = new MsgConsumer();


        try {
            consumer.init(VM_LOCALHOST);
            prod.init(VM_LOCALHOST);
            cons.init(VM_LOCALHOST);
            prod.sendCreatedMessages();
            cons.waitForMsgs(1000);
            consumer.waitForMsgs(1000);
            prod.close();
            cons.close();
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

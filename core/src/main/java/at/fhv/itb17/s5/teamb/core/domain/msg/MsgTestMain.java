package at.fhv.itb17.s5.teamb.core.domain.msg;

import at.fhv.itb17.s5.teamb.persistence.entities.MsgTopic;

import javax.jms.JMSException;
import java.util.LinkedList;
import java.util.List;

@Deprecated
@SuppressWarnings({"squid:MissingDeprecatedCheck", "squid:CommentedOutCodeLine"})
public class MsgTestMain {
    private static final String VM_LOCALHOST = "vm://localhost";

    public static void main(String[] args) {
        MsgTopic system = new MsgTopic("SYSTEM", false);
        MsgTopic rock = new MsgTopic("ROCK", false);
        MsgTopic opera = new MsgTopic("OPERA", false);

        List<MsgTopic> sysRock = new LinkedList<>();
        List<MsgTopic> sysOpera = new LinkedList<>();
        sysRock.add(system);
        sysRock.add(rock);
        sysOpera.add(system);
        sysOpera.add(opera);

        MsgProducer prod = new MsgProducer();
       /* MsgConsumer cons = new MsgConsumer(sysRock);
        MsgConsumer consumer = new MsgConsumer(sysOpera);*/

        try {
            //consumer.init(VM_LOCALHOST);
            prod.init(VM_LOCALHOST);
            //cons.init(VM_LOCALHOST);
            prod.sendCreatedMessages();
            prod.close();
            //cons.close();
            //consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

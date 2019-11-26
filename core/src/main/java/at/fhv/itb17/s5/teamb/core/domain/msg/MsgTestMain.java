package at.fhv.itb17.s5.teamb.core.domain.msg;

public class MsgTestMain {

    public static void main(String[] args) throws Exception{
        thread(new MsgProducer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        Thread.sleep(1000);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        Thread.sleep(1000);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgProducer(), false);
        Thread.sleep(1000);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgConsumer(), false);
        thread(new MsgProducer(), false);
    }

    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}

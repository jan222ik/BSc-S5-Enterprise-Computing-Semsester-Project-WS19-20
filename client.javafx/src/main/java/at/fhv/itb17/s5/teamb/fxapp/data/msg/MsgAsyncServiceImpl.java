package at.fhv.itb17.s5.teamb.fxapp.data.msg;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import org.apache.commons.lang3.NotImplementedException;


import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.LinkedList;
import java.util.List;

public class MsgAsyncServiceImpl implements ExceptionListener, MessageListener, MsgAsyncService {
    @Override
    public List<MsgWrapper> getAllMsgs() {
        return new LinkedList<>();
    }

    @Override
    public boolean hasNewMessages() {
        return false;
    }


    @Override
    public void setPresenter(ApplicationMain applicationMain) {
        throw new NotImplementedException("Will be implemented later");
    }

    @Override
    public void onException(JMSException exception) {
        exception.printStackTrace();
    }

    @Override
    public void onMessage(Message message) {
        throw new NotImplementedException("Will be implemented later");
    }
}

package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;

import java.util.List;
import java.util.function.Consumer;

public class MsgAsyncServiceImpl implements MsgAsyncService {
    @Override
    public List<MsgWrapper> getAllMsgs() {
        return null;
    }

    @Override
    public boolean hasNewMessages() {
        return false;
    }

    @Override
    public void setNotification(Consumer<MsgWrapper> consumer) {

    }

    @Override
    public void setPresenter(ApplicationMain applicationMain) {

    }
}

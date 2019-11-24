package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;

import java.util.List;
import java.util.function.Consumer;

public interface MsgAsyncService {
    List<MsgWrapper> getAllMsgs();

    boolean hasNewMessages();

    void setNotification(Consumer<MsgWrapper> consumer);

    void setPresenter(ApplicationMain applicationMain);
}

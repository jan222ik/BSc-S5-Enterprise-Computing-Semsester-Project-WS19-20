package at.fhv.itb17.s5.teamb.fxapp.data;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;

import java.util.List;

public interface MsgAsyncService {
    List<MsgWrapper> getAllMsgs();

    boolean hasNewMessages();

    void setPresenter(ApplicationMain applicationMain);
}

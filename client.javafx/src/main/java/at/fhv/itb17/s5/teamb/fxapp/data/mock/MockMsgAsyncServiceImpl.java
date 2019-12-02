package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MockMsgAsyncServiceImpl implements MsgAsyncService {

    private static final Logger logger = LogManager.getLogger(MockMsgAsyncServiceImpl.class);
    private final Disposable subscribeDisposable;
    private LinkedList<MsgWrapper> msgWrappers;
    private ApplicationMain presenter;

    public MockMsgAsyncServiceImpl() {
        msgWrappers = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            msgWrappers.add(new MsgWrapper("Topic " + i, "msg " + i, null, LocalDateTime.now(), false, "header " + i));
        }

        Observable<Long> clock = Observable.interval(3, TimeUnit.SECONDS, JavaFxScheduler.platform());
        subscribeDisposable = clock.subscribeOn(JavaFxScheduler.platform()).subscribe(time -> {
            logger.debug("Created new Message");
            int i = time.intValue() % 100;
            MsgWrapper msgWrapper = new MsgWrapper("Topic " + i, "msg " + i, null, LocalDateTime.now(), false, "header " + i);
            msgWrappers.add(msgWrapper);
            if (presenter != null) {
                presenter.showNewMsg(msgWrapper);
            }
        }, Throwable::printStackTrace);
    }

    public void stop() {
        if (!subscribeDisposable.isDisposed()) {
            subscribeDisposable.dispose();
        }
    }

    @Override
    public List<MsgWrapper> getAllMsgs() {
        return msgWrappers;
    }

    @Override
    public boolean hasNewMessages() {
        return false;
    }

    public void setPresenter(ApplicationMain presenter) {
        this.presenter = presenter;
    }
}

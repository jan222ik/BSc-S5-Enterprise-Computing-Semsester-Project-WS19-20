package at.fhv.itb17.s5.teamb.fxapp.data.mock;

import at.fhv.itb17.s5.teamb.fxapp.ApplicationMain;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MockMsgAsyncServiceImpl implements MsgAsyncService {

    private final Disposable subscribeDisposable;
    private LinkedList<MsgWrapper> msgWrappers;
    private ApplicationMain presenter;

    {
        msgWrappers = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            msgWrappers.add(new MsgWrapper("Topic " + i, "msg " + i, null, LocalDateTime.now(), false, "header " + i));
        }
    }

    public MockMsgAsyncServiceImpl() {
        Observable<Long> clock = Observable.interval(3, TimeUnit.SECONDS, JavaFxScheduler.platform());
        subscribeDisposable = clock.subscribeOn(JavaFxScheduler.platform()).subscribe(time -> {
            System.out.println("Iteration");
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

    @Override
    public void setNotification(Consumer<MsgWrapper> consumer) {
        //this.consumer = consumer;
    }

    public void setPresenter(ApplicationMain presenter) {
        this.presenter = presenter;
    }
}

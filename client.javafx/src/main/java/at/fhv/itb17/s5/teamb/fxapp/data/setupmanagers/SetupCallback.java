package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

import io.reactivex.disposables.Disposable;

import java.util.List;

public interface SetupCallback {

    void onNextSetup(String name, int currentStep, int totalSteps);

    void setupFinished(List<Disposable> disposables);

}


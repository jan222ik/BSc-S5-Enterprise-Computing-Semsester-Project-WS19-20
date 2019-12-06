package at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers;

public interface SetupCallback {

    void onNextSetup(String name, int currentStep, int totalSteps);

    void setupFinished();

}


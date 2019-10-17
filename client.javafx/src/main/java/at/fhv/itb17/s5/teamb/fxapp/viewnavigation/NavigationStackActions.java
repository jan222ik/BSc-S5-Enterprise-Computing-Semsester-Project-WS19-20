package at.fhv.itb17.s5.teamb.fxapp.viewnavigation;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModel;

public interface NavigationStackActions<T extends ViewModel> {
    void popToRoot();
    void popLast();
    void push(ContentView<T> view);
    void showTOS();
}

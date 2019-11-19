package at.fhv.itb17.s5.teamb.fxapp.viewnavigation;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModel;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;

public interface NavigationStackActions<T extends ViewModel> {
    NavigationStackActions<T> popToRoot();

    NavigationStackActions<T> popLast();

    NavigationStackActions<T> push(ContentView<T> view);

    void showTOS();

    void changeToMenuItem(ApplicationMenuViews viewIdf, boolean popToRoot, Runnable... onError);

    void logout() throws IllegalAccessException;
}

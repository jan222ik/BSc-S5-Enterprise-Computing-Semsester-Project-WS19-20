package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;

public interface ContentfulViewLifeCycle<T extends ViewModel> {
    default void preDestroy(T viewModel) {
    }

    default void onCreate(T viewModel, NavigationStackActions<T> navActions) {
    }

    default void onReturned(T viewModel) {
    }

    default void beforeMenuSwitch(T viewModel) {
    }
}

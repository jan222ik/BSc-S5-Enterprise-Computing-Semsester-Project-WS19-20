package at.fhv.itb17.s5.teamb.fxapp.viewnavigation.forshow;

import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.RootVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Presenter implements ContentfulViewLifeCycle<RootVM> {
    @FXML private Button backBtn;
    @FXML private Button switch2CartBtn;

    @Override
    public void onCreate(RootVM viewModel, NavigationStackActions<RootVM> navActions) {
        backBtn.setOnAction(ignored ->
                navActions.popLast().showTOS()
        );
        switch2CartBtn.setOnAction(ignored ->
                navActions.changeToMenuItem(ApplicationMenuViews.CART_VIEW,
                        true,
                        () -> NotificationsHelper.error("Error","Could not switch to cart"))
        );
    }

    @Override
    public void preDestroy(RootVM viewModel) {

    }

    @Override
    public void onReturned(RootVM viewModel) {

    }

    @Override
    public void beforeMenuSwitch(RootVM viewModel) {

    }
}

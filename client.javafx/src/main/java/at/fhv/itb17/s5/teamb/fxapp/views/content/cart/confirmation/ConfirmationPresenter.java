package at.fhv.itb17.s5.teamb.fxapp.views.content.cart.confirmation;

import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.CartVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmationPresenter implements ContentfulViewLifeCycle<CartVM> {
    @FXML
    private Button backSearchBtn;

    @Override
    public void onCreate(CartVM viewModel, NavigationStackActions<CartVM> navActions) {
        backSearchBtn.setOnAction(e -> {
            navActions.popToRoot();
            navActions.changeToMenuItem(ApplicationMenuViews.SEARCH_VIEW, () ->
                    NotificationsHelper.error("Internal Error", "Could not change to menu item"));
        });
    }
}

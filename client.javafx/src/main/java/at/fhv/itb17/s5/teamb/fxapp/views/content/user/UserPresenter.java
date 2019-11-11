package at.fhv.itb17.s5.teamb.fxapp.views.content.user;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.RootVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.inject.Inject;

public class UserPresenter implements ContentfulViewLifeCycle<RootVM> {

    @Inject
    private Style style;

    @FXML
    private Label usernameL;
    @FXML
    private Button logoutBtn;

    @Override
    public void onCreate(RootVM viewModel, NavigationStackActions<RootVM> navActions) {
        style.hoverBtn(logoutBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.ERROR().asPaint());
        logoutBtn.setOnAction(e -> navActions.logout());
    }

    @Override
    public void onReturned(RootVM viewModel) {
        usernameL.setText(viewModel.getUsername());
    }
}

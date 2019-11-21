package at.fhv.itb17.s5.teamb.fxapp.views.content.msgcon;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.MsgTopicVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MsgConPresenter implements ContentfulViewLifeCycle<MsgTopicVM> {
    @FXML
    private AnchorPane rootPlane;
    @FXML
    private VBox msgprodVbox;
    @FXML
    private Button msgprodBtn;

    @Override
    public void onCreate(MsgTopicVM viewModel, NavigationStackActions<MsgTopicVM> navActions) {
        boolean publish = viewModel.mayPublish();
        if (!publish) {
            rootPlane.getChildren().remove(msgprodVbox);
        } else {
            msgprodBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.MSG_PROD_VIEW, true));
        }
    }
}

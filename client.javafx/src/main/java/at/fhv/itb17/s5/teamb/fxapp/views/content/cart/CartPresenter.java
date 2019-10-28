package at.fhv.itb17.s5.teamb.fxapp.views.content.cart;

import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModelImpl;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CartPresenter implements ContentfulViewLifeCycle<ViewModelImpl> {

    @FXML
    private Button openItemViewIndexOutBound;
    @FXML
    private Button openItemViewIndex0;
    @FXML
    private Button openItemViewIndex2;
    @FXML
    private Button backBtn;
    @FXML
    private TextField syncText;

    @Override
    public void onCreate(ViewModelImpl viewModel, NavigationStackActions<ViewModelImpl> navActions) {
        String textDemoString = viewModel.getTextDemoString();
        if (textDemoString != null) {
            syncText.setText(textDemoString);
        }
        backBtn.setOnAction(e -> {
            String text = syncText.getText();
            viewModel.saveTextDemo(text);
            navActions.popLast();
            navActions.showTOS();
        });
        openItemViewIndex0.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.SEARCH_VIEW));
        openItemViewIndex2.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.DEMO_VIEW));
        openItemViewIndexOutBound.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.NOT_EXISTING, () -> NotificationsHelper.error("Error", "Could not switch to MenuItem")));
    }
}

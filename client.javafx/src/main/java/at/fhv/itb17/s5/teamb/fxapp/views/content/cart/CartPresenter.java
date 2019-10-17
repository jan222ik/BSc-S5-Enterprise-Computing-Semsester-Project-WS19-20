package at.fhv.itb17.s5.teamb.fxapp.views.content.cart;

import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModelImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CartPresenter implements ContentfulViewLifeCycle<ViewModelImpl> {

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
    }
}

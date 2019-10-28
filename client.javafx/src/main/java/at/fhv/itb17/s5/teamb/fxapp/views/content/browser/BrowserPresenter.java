package at.fhv.itb17.s5.teamb.fxapp.views.content.browser;

import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModelImpl;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.CartView;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserPresenter implements ContentfulViewLifeCycle<ViewModelImpl> {

    private static final Logger logger = LogManager.getLogger(BrowserPresenter.class);
    @FXML
    private TextField syncText;
    @FXML
    private Button nextBTN;


    @Override
    public void onCreate(ViewModelImpl viewModel, NavigationStackActions<ViewModelImpl> navActions) {
        nextBTN.setOnAction(e -> {
            logger.debug(LogMarkers.UI_EVENT, "NextBTN clicked");
            viewModel.saveTextDemo(syncText.getText());
            navActions.push(new CartView());
            navActions.showTOS();
        });
    }

    @Override
    public void onReturned(ViewModelImpl viewModel) {
        if (viewModel == null) {
            logger.error("Model not present");
        } else {
            String textDemoString = viewModel.getTextDemoString();
            if (textDemoString != null) {
                syncText.setText(textDemoString);
            }
        }
    }
}

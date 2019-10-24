package at.fhv.itb17.s5.teamb.fxapp.views.demo;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModel;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


@SuppressWarnings("ClassInitializerMayBeStatic")
public class DemoPresenter implements ContentfulViewLifeCycle {
    @Inject
    private static Style style;
    @FXML
    private Label helloWorld;

    private static int numberOfInstance = 0;
    private static final Logger logger = LogManager.getLogger(DemoPresenter.class);

    {
        numberOfInstance++;
    }

    @Override
    public void onCreate(ViewModel viewModel, NavigationStackActions navActions) {
        helloWorld.setText("Hello World #" + numberOfInstance);
        helloWorld.setTextFill(style.ON_SURFACE().asPaint());
        logger.debug(LogMarkers.UI_LIFECYCLE, "ON_CREATE\t{}", this);
    }
}

package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuView;
import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationMain extends Application {

    private static final Logger logger = LogManager.getLogger(ApplicationMain.class);

    public void start(Stage primaryStage) throws Exception {
        Thread.currentThread().setName("FX Main");
        Style style = new Style();
        Injector.setModelOrService(Style.class, style);
        //new Main().main(new LinkedList<>());
        logger.info("Application Started");
        MenuView view = new MenuView();
        Scene main = new Scene(view.getView());
        primaryStage.setTitle("#PLACEHOLDER");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.toFront();
        final String text = "A happy little text";
        NotificationsHelper.inform("Info", text, NotificationsHelper.DisplayDuration.SHORT);
        NotificationsHelper.error("Error", text);
        NotificationsHelper.warn("Warning", text, NotificationsHelper.DisplayDuration.LONG);
        NotificationsHelper.confirm("Confirm", text);
    }
}

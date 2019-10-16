package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.views.demo.DemoView;
import at.fhv.itb17.s5.teamb.persistence.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class ApplicationMain extends Application {

    private static final Logger logger = LogManager.getLogger(ApplicationMain.class);

    public void start(Stage primaryStage) throws Exception {
        Thread.currentThread().setName("FX Main");
        new Main().main(new LinkedList<>());
        logger.info("Application Started");
        DemoView view = new DemoView();
        Scene main = new Scene(view.getView());
        primaryStage.setTitle(view.getTitle());
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.toFront();
        NotificationsHelper.inform("Info", "A happy little text", NotificationsHelper.DisplayDuration.INDEFINITE);
        NotificationsHelper.error("Error", "A happy little text");
        NotificationsHelper.warn("Warning", "A happy little text", NotificationsHelper.DisplayDuration.LONG);
        NotificationsHelper.confirm("Confirm", "A happy little text");
    }
}

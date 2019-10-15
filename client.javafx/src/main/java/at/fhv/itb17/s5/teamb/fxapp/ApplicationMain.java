package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.views.demo.DemoView;
import at.fhv.itb17.s5.teamb.persistence.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.LinkedList;

public class ApplicationMain extends Application {

    public void start(Stage primaryStage) throws Exception {
        new Main().main(new LinkedList<>());
        DemoView view = new DemoView();
        Scene main = new Scene(view.getView());
        primaryStage.setTitle(view.getTitle());
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.toFront();
    }
}

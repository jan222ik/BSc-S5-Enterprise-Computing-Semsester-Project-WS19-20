package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.mock.MockSearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMISearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuView;
import at.fhv.itb17.s5.teamb.util.ArgumentParser;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationMain extends Application {

    private static final Logger logger = LogManager.getLogger(ApplicationMain.class);
    private ArgumentParser args;

    @Override
    public void init() throws Exception {
        super.init();
        args = new ArgumentParser();
        args.parseArgs(getParameters().getRaw(), '=');
    }

    public void start(Stage primaryStage) throws Exception {
        Thread.currentThread().setName("FX Main");
        final Style[] style = {new Style()};
        args.checkForKeyword("-light", a -> {
            final String white = "#FFFFFF";
            final String black = "#000000";
            style[0] = Style.builder()
                    .primary("#6200EE").onPrimary(black)
                    .secondary("#03DAC6").onSecondary(black)
                    .background(white).onBackground(black)
                    .surface(white).onSurface(black)
                    .error("#B00020").onError(white).getStyle();
        });
        Injector.setModelOrService(Style.class, style[0]);
        SearchService service = (args.containsKeyword("-mock")) ? new MockSearchServiceImpl() : new RMISearchServiceImpl("localhost", 2345);
        Injector.setModelOrService(SearchService.class, service);
        logger.info(LogMarkers.APPLICATION, "Application Started");
        boolean withLogin = args.containsKeyword("-login");
        LoginView loginView = new LoginView();
        MenuView menuView = new MenuView();
        primaryStage.initStyle(
                args.containsKeyword("-decorated") ? StageStyle.DECORATED : StageStyle.UNDECORATED
        );
        Runnable afterLogin = () -> {
            Scene scene = new Scene(
                    menuView.getView(),
                    Double.parseDouble(args.getArgValue("-width", "800")),
                    Double.parseDouble(args.getArgValue("-height", "400"))
            );
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
        };
        ((LoginPresenter) loginView.getPresenter()).setNextSceneCallback(afterLogin);
        if (withLogin) {
            Scene main = new Scene(loginView.getView(), 600D, 300D);
            primaryStage.setScene(main);
            primaryStage.setTitle("#placeholder");
        } else {
            afterLogin.run();
        }
        primaryStage.show();
        primaryStage.toFront();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        logger.info(LogMarkers.APPLICATION, "Application Stopped Gracefully");
    }
}

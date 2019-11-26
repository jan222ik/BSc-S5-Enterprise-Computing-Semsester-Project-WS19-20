package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.fxapp.data.BookingService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgTopicService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import at.fhv.itb17.s5.teamb.fxapp.data.mock.MockBookingServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.mock.MockMsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.mock.MockMsgTopicServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.mock.MockSearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIBookingServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIController;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMISearchServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMITopicServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuView;
import at.fhv.itb17.s5.teamb.util.ArgumentParser;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class ApplicationMain extends Application {

    private static final Logger logger = LogManager.getLogger(ApplicationMain.class);
    private ArgumentParser args;

    private Consumer<String> createMenu;
    private RMIController rmiController;

    @Override
    public void init() throws Exception {
        super.init();
        args = new ArgumentParser();
        args.parseArgs(getParameters().getRaw(), '=');
    }

    public void start(Stage primaryStage) throws Exception {
        Thread.currentThread().setName("FX Main");
        Injector.setModelOrService(Style.class, new Style());
        SearchService searchService;
        BookingService bookingService;
        MsgTopicService topicService;
        MsgAsyncService msgAsyncService;
        if (args.containsKeyword("-mock")) {
            searchService = new MockSearchServiceImpl();
            bookingService = new MockBookingServiceImpl();
            topicService = new MockMsgTopicServiceImpl();
            msgAsyncService = new MockMsgAsyncServiceImpl();
        } else {
            rmiController = new RMIController();
            searchService = new RMISearchServiceImpl(rmiController);
            bookingService = new RMIBookingServiceImpl(rmiController);
            topicService = new RMITopicServiceImpl(rmiController);
            if (args.containsKeyword("-mockMsg")) {
                msgAsyncService = new MockMsgAsyncServiceImpl();
            } else {
                msgAsyncService = new MsgAsyncServiceImpl();
            }
        }
        Injector.setModelOrService(SearchService.class, searchService);
        Injector.setModelOrService(BookingService.class, bookingService);
        Injector.setModelOrService(MsgTopicService.class, topicService);
        Injector.setModelOrService(MsgAsyncService.class, msgAsyncService);
        Injector.setModelOrService(RMIController.class, rmiController);
        boolean withLogin = args.containsKeyword("-login");

        Runnable createLogin = () -> generateLogin(primaryStage);
        createMenu = (String name) -> generateMenu(primaryStage, name);

        if (withLogin) {
            createLogin.run();
        } else {
            final String backdoorUsername = "backdoor";
            if (rmiController != null) {
                rmiController.connect("localhost", 2345);
            }
            searchService.init();
            bookingService.doLoginBooking(backdoorUsername, "backdoorPWD");
            topicService.doLoginMsgTopic(backdoorUsername, "backdoorPWD");
            msgAsyncService.setNotification(msg -> {
                NotificationsHelper.inform("New Message", "Message in topic " + msg.getTopicName());
            });
            msgAsyncService.setPresenter(this);
            createMenu.accept(backdoorUsername);
        }
        showStage(primaryStage);
        logger.info(LogMarkers.APPLICATION, "Application Started");
    }

    @NotNull
    @SuppressWarnings("UnusedReturnValue")
    private MenuView generateMenu(Stage primary, String name) {
        MenuView menuView = new MenuView();
        Scene scene = new Scene(
                menuView.getView(),
                Double.parseDouble(args.getArgValue("-width", "800")),
                Double.parseDouble(args.getArgValue("-height", "400"))
        );
        MenuPresenter presenter = (MenuPresenter) menuView.getPresenter();
        presenter.setLogoutCallback(() -> generateLogin(primary));
        presenter.setUsername(name);
        showScene(primary, scene, "#placeholder");
        return menuView;
    }

    @NotNull
    @SuppressWarnings("UnusedReturnValue")
    private LoginView generateLogin(@NotNull Stage primary) {
        LoginView loginView = new LoginView();
        ((LoginPresenter) loginView.getPresenter()).setNextSceneCallback(createMenu);
        Scene scene = new Scene(loginView.getView(), 600D, 300D);
        showScene(primary, scene, "Login");
        return loginView;
    }

    private void showScene(Stage primary, Scene scene, String title) {
        primary.setScene(scene);
        primary.setTitle(title);
    }

    private void showStage(@NotNull Stage primary) throws FileNotFoundException {
        primary.initStyle(
                args.containsKeyword("-decorated") ? StageStyle.DECORATED : StageStyle.UNDECORATED
        );
        File file = new File("client.javafx/src/main/resources/icon.png");
        primary.getIcons().add(new Image(new FileInputStream(file)));
        primary.show();
        primary.toFront();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        if (rmiController != null) {
            rmiController.stopRMI();
        }
        logger.info(LogMarkers.APPLICATION, "Application Stopped Gracefully");
    }

    public void showNewMsg(MsgWrapper msg) {
        NotificationsHelper.inform("New Message", "Message in topic " + msg.getTopicName());
        //Toolkit.getDefaultToolkit().beep();
    }
}

package at.fhv.itb17.s5.teamb.fxapp;

import at.fhv.itb17.s5.teamb.core.domain.msg.MsgServiceCoreImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgAsyncService;
import at.fhv.itb17.s5.teamb.fxapp.data.MsgWrapper;
import at.fhv.itb17.s5.teamb.fxapp.data.ejb.EJBController;
import at.fhv.itb17.s5.teamb.fxapp.data.msg.MsgAsyncServiceImpl;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.RMIController;
import at.fhv.itb17.s5.teamb.fxapp.data.rmi.SecManager;
import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.BeanManager;
import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.RmiManager;
import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.SetupCallback;
import at.fhv.itb17.s5.teamb.fxapp.data.setupmanagers.SetupManager;
import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.login.LoginView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuView;
import at.fhv.itb17.s5.teamb.util.ArgumentParser;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import com.airhacks.afterburner.injection.Injector;
import io.reactivex.disposables.Disposable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.jms.JMSException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.util.List;
import java.util.function.Consumer;

public class ApplicationMain extends Application implements SetupCallback {

    private static final Logger logger = LogManager.getLogger(ApplicationMain.class);
    private ArgumentParser args;

    private Consumer<String> createMenu;
    private RMIController rmiController;
    private EJBController ejbController;
    private MsgAsyncService msgAsyncService;
    private SetupManager setupManager;

    @Override
    public void init() throws Exception {
        super.init();
        args = new ArgumentParser();
        args.parseArgs(getParameters().getRaw(), '=');
    }

    private void setSecurityPolicy() {
        Policy.setPolicy(
                new Policy() {
                    @Override
                    public PermissionCollection getPermissions(CodeSource codesource) {
                        Permissions p = new Permissions();
                        p.add(new AllPermission());
                        return p;
                    }
                });
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

    public void start(Stage primaryStage) throws Exception {
        setSecurityPolicy();
        Thread.currentThread().setName("Fred");
        System.setSecurityManager(new SecManager());
        Injector.setModelOrService(Style.class, new Style());

        if (new Boolean(args.getArgValue("-ejb", "false"))) {
            setupManager = new BeanManager();
        } else{
            setupManager = new RmiManager();
        }
        setupManager.setMsgNotificationPresenter(this);
        boolean b = setupManager.create();
        if (!b) {
            throw new RuntimeException("Error in manager.create");
        }
        setupManager.setCallbackConsumer(this);

        Injector.setModelOrService(SetupManager.class, setupManager);
        Injector.setModelOrService(RMIController.class, rmiController);
        Injector.setModelOrService(EJBController.class, ejbController);

        Runnable createLogin = () -> generateLogin(primaryStage);
        createMenu = (String name) -> generateMenu(primaryStage, name);

        createLogin.run();

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
        setupManager.close();
        logger.info(LogMarkers.APPLICATION, "Application Stopped Gracefully");
    }

    public void showNewMsg(MsgWrapper msg) {
        NotificationsHelper.inform("New Message", "Message in topic " + msg.getTopicName());
    }

    @Override
    public void onNextSetup(String name, int currentStep, int totalSteps) {
        logger.info("Setup Step {} of {}: {}", currentStep, totalSteps, name);
        NotificationsHelper.inform("Setup", "Step " + currentStep + " of " + totalSteps + ": " + name);
    }

    @Override
    public void setupFinished(List<Disposable> disposables) {
        disposables.forEach(Disposable::dispose);
    }

}

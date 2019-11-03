package at.fhv.itb17.s5.teamb.fxapp.util;

import at.fhv.itb17.s5.teamb.util.LogMarkers;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class WindowEventHelper {

    private static final Logger logger = LogManager.getLogger(WindowEventHelper.class);
    private static MouseEvent originEvent;
    private static double xOffset;
    private static double yOffset;

    private WindowEventHelper() {
    }

    public static void closeApplicationImpl(@NotNull Button closeBtn) {
        closeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "EXIT pressed");
            Platform.exit();
        });
    }

    public static void minimizeApplicationImpl(@NotNull Button minimizeBtn) {
        minimizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MINIMIZE pressed");
            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
    }

    public static void maximizeApplicationImpl(@NotNull Button maximizeBtn) {
        maximizeBtn.setOnAction(e -> {
            logger.debug(LogMarkers.WINDOW, "MAXIMIZE pressed");
            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        });
    }

    public static void draggableApplicationWindowImpl(@NotNull Pane pane) {
        pane.setOnMousePressed(e -> {
            originEvent = e;
            Stage stage = (Stage) ((Pane) originEvent.getSource()).getScene().getWindow();
            if (!stage.isMaximized()) {
                xOffset = stage.getX() - originEvent.getScreenX();
                yOffset = stage.getY() - originEvent.getScreenY();
            }
        });
        pane.setOnDragDetected(e -> {
            Stage stage = (Stage) ((Pane) originEvent.getSource()).getScene().getWindow();
            if (stage.isMaximized()) {
                double widthPercent = originEvent.getSceneX() / stage.getWidth();
                stage.setMaximized(false);
                stage.setX(originEvent.getScreenX() - stage.getWidth() * widthPercent);
                stage.setY(originEvent.getY());
                xOffset = stage.getX() - originEvent.getScreenX();
                yOffset = stage.getY() - originEvent.getScreenY();
            }
        });
        pane.setOnMouseDragged(e -> {
            Stage stage = (Stage) ((Pane) e.getSource()).getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
    }

}

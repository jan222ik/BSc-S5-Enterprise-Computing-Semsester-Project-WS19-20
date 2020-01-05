package at.fhv.itb17.s5.teamb.fxapp.util;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper.DisplayDuration.SHORT;


@SuppressWarnings("WeakerAccess")
public final class NotificationsHelper {

    private NotificationsHelper() {/*Util Class*/}

    public enum DisplayType {
        INFO, WARNING, ERROR, CONFIRMATION;

        protected void show(Notifications notification) {
            switch (this) {
                case INFO:
                    notification.show();
                    break;
                case WARNING:
                    notification.showWarning();
                    break;
                case ERROR:
                    notification.showError();
                    break;
                case CONFIRMATION:
                    notification.showConfirm();
                    break;
            }
        }
    }

    public enum DisplayDuration {
        SHORT(Duration.seconds(4)),
        LONG(Duration.seconds(10)),
        INDEFINITE(Duration.INDEFINITE);

        private Duration duration;

        public Duration getDuration() {
            return duration;
        }

        DisplayDuration(Duration duration) {
            this.duration = duration;
        }
    }

    public static void error(String title, String text) {
        showNotification(title, text, DisplayType.ERROR, SHORT, null);
    }

    public static void error(String title, String text, @NotNull DisplayDuration durationType) {
        showNotification(title, text, DisplayType.ERROR, durationType, null);
    }

    public static void warn(String title, String text) {
        showNotification(title, text, DisplayType.WARNING, SHORT, null);
    }

    public static void warn(String title, String text, @NotNull DisplayDuration durationType) {
        showNotification(title, text, DisplayType.WARNING, durationType, null);
    }

    public static void confirm(String title, String text) {
        showNotification(title, text, DisplayType.CONFIRMATION, SHORT, null);
    }

    public static void confirm(String title, String text, @NotNull DisplayDuration durationType) {
        showNotification(title, text, DisplayType.CONFIRMATION, durationType, null);
    }

    public static void inform(String title, String text) {
        showNotification(title, text, DisplayType.INFO, SHORT, null);
    }

    public static void inform(String title, String text, @NotNull DisplayDuration durationType) {
        showNotification(title, text, DisplayType.INFO, durationType, null);
    }

    public static void showNotification(String title, String text, @NotNull DisplayType type,
                                        @NotNull DisplayDuration durationType, @Nullable Node graphic) {
        Notifications notification = Notifications.create();
        notification
                .title(title)
                .text(text)
                .position(Pos.CENTER_RIGHT)
                .hideAfter(durationType.getDuration())
                .onAction(e -> notification.hideAfter(Duration.millis(0)))
                .darkStyle()
                .graphic(graphic);
        type.show(notification);
    }
}

package at.fhv.itb17.s5.teamb.fxapp.util;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import static at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper.DisplayDuration.SHORT;


@SuppressWarnings("WeakerAccess")
public final class NotificationsHelper {

    private NotificationsHelper() {/*Util Class*/}

    public enum NotificationType {
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
        showNotification(title, text, NotificationType.ERROR, SHORT, null);
    }

    public static void error(String title, String text, DisplayDuration durationType) {
        showNotification(title, text, NotificationType.ERROR, durationType, null);
    }

    public static void warn(String title, String text) {
        showNotification(title, text, NotificationType.WARNING, SHORT, null);
    }

    public static void warn(String title, String text, DisplayDuration durationType) {
        showNotification(title, text, NotificationType.WARNING, durationType, null);
    }

    public static void confirm(String title, String text) {
        showNotification(title, text, NotificationType.CONFIRMATION, SHORT, null);
    }

    public static void confirm(String title, String text, DisplayDuration durationType) {
        showNotification(title, text, NotificationType.CONFIRMATION, durationType, null);
    }

    public static void inform(String title, String text) {
        showNotification(title, text, NotificationType.INFO, SHORT, null);
    }

    public static void inform(String title, String text, DisplayDuration durationType) {
        showNotification(title, text, NotificationType.INFO, durationType, null);
    }

    public static void showNotification(String title, String text, NotificationType type, DisplayDuration durationType, Node graphic) {
        Notifications notification = Notifications.create();
        notification
                .title(title)
                .text(text)
                .position(Pos.TOP_RIGHT)
                .hideAfter(durationType.getDuration())
                .onAction(e -> notification.hideAfter(Duration.millis(0)))
                .darkStyle()
                .graphic(graphic);
        type.show(notification);
    }
}

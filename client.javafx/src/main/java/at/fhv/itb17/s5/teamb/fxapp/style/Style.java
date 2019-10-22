package at.fhv.itb17.s5.teamb.fxapp.style;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

@SuppressWarnings({"squid:S1192", "squid:ClassVariableVisibilityCheck", "squid:S00116", "squid:S00100", "WeakerAccess"})
public class Style {
    public String PRIMARY_RGB = "#BB86FC";
    public String SECONDARY_RGB = "#03DAC6";
    public String BACKGROUND_RGB = "#121212";
    public String SURFACE_RGB = "#2F2F2F";
    public String ERROR_RGB = "#CF6679";

    public String ON_PRIMARY_RGB = "#000000";
    public String ON_SECONDARY_RGB = "#000000";
    public String ON_BACKGROUND_RGB = "#FFFFFF";
    public String ON_SURFACE_RGB = "#FFFFFF";
    public String ON_ERROR_RGB = "#000000";

    public Paint PRIMARY_PAINT() {
        return Paint.valueOf(PRIMARY_RGB);
    }

    public Paint SECONDARY_PAINT() {
        return Paint.valueOf(SECONDARY_RGB);
    }

    public Paint BACKGROUND_PAINT() {
        return Paint.valueOf(BACKGROUND_RGB);
    }

    public Paint SURFACE_PAINT() {
        return Paint.valueOf(SURFACE_RGB);
    }

    public Paint ERROR_PAINT() {
        return Paint.valueOf(ERROR_RGB);
    }

    public Paint ON_PRIMARY_PAINT() {
        return Paint.valueOf(ON_PRIMARY_RGB);
    }

    public Paint ON_SECONDARY_PAINT() {
        return Paint.valueOf(ON_SECONDARY_RGB);
    }

    public Paint ON_BACKGROUND_PAINT() {
        return Paint.valueOf(ON_BACKGROUND_RGB);
    }

    public Paint ON_SURFACE_PAINT() {
        return Paint.valueOf(ON_SURFACE_RGB);
    }

    public Paint ON_ERROR_PAINT() {
        return Paint.valueOf(ON_ERROR_RGB);
    }

    public void hoverBtn(Button btn, Background defaultB, Paint onDefaultB, Background hoverB, Paint onHoverB) {
        hoverBtn(btn, defaultB, onDefaultB, hoverB, onHoverB, null);
    }

    public void hoverBtn(Button btn, Background defaultB, Paint onDefaultB, Background hoverB, Paint onHoverB, SimpleBooleanProperty immutableOnToggle) {
        btn.setBackground(defaultB);
        btn.setTextFill(onDefaultB);
        btn.setOnMouseEntered(e -> {
            if (immutableOnToggle == null || !immutableOnToggle.get()) {
                btn.setBackground(hoverB);
                btn.setTextFill(onHoverB);
            }
        });
        btn.setOnMouseExited(e -> {
            if (immutableOnToggle == null ||!immutableOnToggle.get()) {
                btn.setBackground(defaultB);
                btn.setTextFill(onDefaultB);
            }
        });
    }

    public static StyleBuilder builder() {
        return new StyleBuilder();
    }

    static class StyleBuilder {
        private Style style;

        StyleBuilder() {
            style = new Style();
        }

        public StyleBuilder primary(String rgb) {
            style.PRIMARY_RGB = rgb;
            return this;
        }

        public StyleBuilder secondary(String rgb) {
            style.SECONDARY_RGB = rgb;
            return this;
        }

        public StyleBuilder background(String rgb) {
            style.BACKGROUND_RGB = rgb;
            return this;
        }

        public StyleBuilder surface(String rgb) {
            style.SURFACE_RGB = rgb;
            return this;
        }

        public StyleBuilder error(String rgb) {
            style.ERROR_RGB = rgb;
            return this;
        }

        public StyleBuilder onPrimary(String rgb) {
            style.ON_PRIMARY_RGB = rgb;
            return this;
        }

        public StyleBuilder onSecondary(String rgb) {
            style.ON_SECONDARY_RGB = rgb;
            return this;
        }

        public StyleBuilder onBackground(String rgb) {
            style.ON_BACKGROUND_RGB = rgb;
            return this;
        }

        public StyleBuilder onSurface(String rgb) {
            style.ON_SURFACE_RGB = rgb;
            return this;
        }

        public StyleBuilder onError(String rgb) {
            style.ON_ERROR_RGB = rgb;
            return this;
        }

    }

}

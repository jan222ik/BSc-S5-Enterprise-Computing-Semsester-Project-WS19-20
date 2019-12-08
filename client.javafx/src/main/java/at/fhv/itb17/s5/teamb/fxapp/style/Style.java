package at.fhv.itb17.s5.teamb.fxapp.style;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

@SuppressWarnings({"squid:S1192", "squid:ClassVariableVisibilityCheck", "squid:S00116", "squid:S00100", "WeakerAccess"})
public class Style {
    private StyleBlock PRIMARY = new StyleBlock("#BB86FC");
    private StyleBlock SECONDARY = new StyleBlock("#03DAC6");
    private StyleBlock BACKGROUND = new StyleBlock("#121212");
    private StyleBlock SURFACE = new StyleBlock("#2F2F2F");
    private StyleBlock ERROR = new StyleBlock("#CF6679");

    private StyleBlock ON_PRIMARY = new StyleBlock("#000000");
    private StyleBlock ON_SECONDARY = new StyleBlock("#000000");
    private StyleBlock ON_BACKGROUND = new StyleBlock("#FFFFFF");
    private StyleBlock ON_SURFACE = new StyleBlock("#FFFFFF");
    private StyleBlock ON_ERROR = new StyleBlock("#000000");

    public StyleBlock PRIMARY() {
        return PRIMARY;
    }

    public StyleBlock SECONDARY() {
        return SECONDARY;
    }

    public StyleBlock BACKGROUND() {
        return BACKGROUND;
    }

    public StyleBlock SURFACE() {
        return SURFACE;
    }

    public StyleBlock ERROR() {
        return ERROR;
    }

    public StyleBlock ON_PRIMARY() {
        return ON_PRIMARY;
    }

    public StyleBlock ON_SECONDARY() {
        return ON_SECONDARY;
    }

    public StyleBlock ON_BACKGROUND() {
        return ON_BACKGROUND;
    }

    public StyleBlock ON_SURFACE() {
        return ON_SURFACE;
    }

    public StyleBlock ON_ERROR() {
        return ON_ERROR;
    }

    public void hoverBtn(Labeled btn, Background defaultB, Paint onDefaultB, Background hoverB, Paint onHoverB) {
        hoverBtn(btn, defaultB, onDefaultB, hoverB, onHoverB, null);
    }

    public void hoverBtn(Labeled btn, Background defaultB, Paint onDefaultB, Background hoverB, Paint onHoverB, SimpleBooleanProperty temporalImmutable) {
        btn.setBackground(defaultB);
        btn.setTextFill(onDefaultB);
        btn.setOnMouseEntered(e -> {
            if (temporalImmutable == null || !temporalImmutable.get()) {
                btn.setBackground(hoverB);
                btn.setTextFill(onHoverB);
            }
        });
        btn.setOnMouseExited(e -> {
            if (temporalImmutable == null || !temporalImmutable.get()) {
                btn.setBackground(defaultB);
                btn.setTextFill(onDefaultB);
            }
        });
    }

    public static class StyleBlock {
        private Paint paint;
        private BackgroundFill backgroundFill = null;
        private Background background = null;

        private String rgb;

        public StyleBlock(String rgb) {
            this.rgb = rgb;
        }

        public Background asBackground() {
            if (background == null) {
                background = new Background(this.asBackgroundFill());
            }
            return background;
        }

        public BackgroundFill asBackgroundFill() {
            if (backgroundFill == null) {
                backgroundFill = new BackgroundFill(this.asPaint(), null, null);
            }
            return backgroundFill;
        }

        public Paint asPaint() {
            if (paint == null) {
                paint = Paint.valueOf(this.asRGB());
            }
            return paint;
        }

        public String asRGB() {
            return rgb;
        }
    }


    public static StyleBuilder builder() {
        return new StyleBuilder();
    }

    public static class StyleBuilder {
        private Style style;

        StyleBuilder() {
            style = new Style();
        }

        public StyleBuilder primary(String rgb) {
            style.PRIMARY = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder secondary(String rgb) {
            style.SECONDARY = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder background(String rgb) {
            style.BACKGROUND = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder surface(String rgb) {
            style.SURFACE = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder error(String rgb) {
            style.ERROR = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder onPrimary(String rgb) {
            style.ON_PRIMARY = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder onSecondary(String rgb) {
            style.ON_SECONDARY = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder onBackground(String rgb) {
            style.ON_BACKGROUND = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder onSurface(String rgb) {
            style.ON_SURFACE = new StyleBlock(rgb);
            return this;
        }

        public StyleBuilder onError(String rgb) {
            style.ON_ERROR = new StyleBlock(rgb);
            return this;
        }

        public Style getStyle() {
            return style;
        }
    }

}

package at.fhv.itb17.s5.teamb.util;

import org.apache.logging.log4j.MarkerManager;

public abstract class LogMarkers {
    public static final MarkerManager.Log4jMarker UI = new MarkerManager.Log4jMarker("UI");
    public static final MarkerManager.Log4jMarker UI_EVENT = new MarkerManager.Log4jMarker("UI-EVENT");
    public static final MarkerManager.Log4jMarker UI_LIFECYCLE = new MarkerManager.Log4jMarker("UI-LC");

    private LogMarkers() {
    }
}

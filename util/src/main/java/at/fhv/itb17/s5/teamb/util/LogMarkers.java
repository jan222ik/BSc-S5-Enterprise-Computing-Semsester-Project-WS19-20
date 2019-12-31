package at.fhv.itb17.s5.teamb.util;

import org.apache.logging.log4j.MarkerManager;

import java.io.Serializable;

@SuppressWarnings("ClassMayBeInterface")
public abstract class LogMarkers implements Serializable {

    public static final MarkerManager.Log4jMarker UI = new MarkerManager.Log4jMarker("UI");
    public static final MarkerManager.Log4jMarker UI_EVENT = new MarkerManager.Log4jMarker("UI-EVENT");
    public static final MarkerManager.Log4jMarker UI_LIFECYCLE = new MarkerManager.Log4jMarker("UI-LC");
    public static final MarkerManager.Log4jMarker UI_NAV = new MarkerManager.Log4jMarker("UI-NAV");
    public static final MarkerManager.Log4jMarker WINDOW = new MarkerManager.Log4jMarker("Window-Evt");
    public static final MarkerManager.Log4jMarker APPLICATION = new MarkerManager.Log4jMarker("APP");
    public static final MarkerManager.Log4jMarker DB = new MarkerManager.Log4jMarker("DB");
    public static final MarkerManager.Log4jMarker RMI_CONTROLLER = new MarkerManager.Log4jMarker("CON:RMI");
    public static final MarkerManager.Log4jMarker EJB_CONTROLLER = new MarkerManager.Log4jMarker("CON:EJB");

    private LogMarkers() {
    }
}

package at.fhv.itb17.s5.teamb.fxapp.util;

import com.airhacks.afterburner.views.FXMLView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Creator for popup windows.
 */
public final class PopupHelper {
    private PopupHelper() {/*Util Class*/}


    /**
     * Creates a popup window.
     *
     * @param view         View of popup window.
     * @param title        Title of popup window.
     * @param icon         Icon of popup window.
     * @param owner        Window parent of popup window.
     * @param initModality Modality of popup window.
     * @param functions    VarArg param all consumers get executed on stage.
     * @return Stage, which has been created.
     */
    @SafeVarargs
    public static Stage create(@NotNull FXMLView view, @NotNull String title,
                               @NotNull Image icon, @NotNull Window owner,
                               @NotNull Modality initModality, @NotNull Consumer<Stage>... functions) {
        Stage popupWindow = new Stage();
        popupWindow.setScene(new Scene(view.getView()));
        popupWindow.setTitle(title);
        popupWindow.initOwner(owner);
        popupWindow.getIcons().add(icon);
        popupWindow.initModality(initModality);
        for (Consumer<Stage> consumer : functions) {
            consumer.accept(popupWindow);
        }
        return popupWindow;
    }


}

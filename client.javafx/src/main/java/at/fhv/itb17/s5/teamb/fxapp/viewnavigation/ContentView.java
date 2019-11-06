package at.fhv.itb17.s5.teamb.fxapp.viewnavigation;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModel;
import at.fhv.itb17.s5.teamb.util.LogMarkers;
import com.airhacks.afterburner.views.FXMLView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"squid:MaximumInheritanceDepth", "WeakerAccess"})
public abstract class ContentView<T extends ViewModel> extends FXMLView {

    private static final Logger logger = LogManager.getLogger(ContentView.class);

    public void preDestroy(T viewModel) {
        logger.debug(LogMarkers.UI_LIFECYCLE, "PRE_DESTROY:\t{}\t{}", this, viewModel);
        getCastedPresenter().preDestroy(viewModel);
    }

    public void onCreate(T viewModel, NavigationStackActions<T> navActions) {
        logger.debug(LogMarkers.UI_LIFECYCLE, "ON_CREATE:\t{}\t{}", this, viewModel);
        getCastedPresenter().onCreate(viewModel, navActions);
    }

    public void onReturned(T viewModel) {
        logger.debug(LogMarkers.UI_LIFECYCLE, "ON_RETURNED:\t{}\t{}", this, viewModel);
        getCastedPresenter().onReturned(viewModel);
    }

    public void beforeMenuSwitch(T viewModel) {
        logger.debug(LogMarkers.UI_LIFECYCLE, "BEFORE_MENU_SWITCH:\t{}\t{}", this, viewModel);
        getCastedPresenter().beforeMenuSwitch(viewModel);
    }

    private ContentfulViewLifeCycle<T> getCastedPresenter() {
        Object presenter = super.getPresenter();
        if (!(presenter instanceof ContentfulViewLifeCycle)) {
            throw new RuntimeException("<T extends ContentView> must have a presenter " +
                    "implementing ContentfulViewLifeCycle<T extends ContentView>");
        } else {
            //noinspection unchecked
            return (ContentfulViewLifeCycle<T>) presenter;
        }
    }
}

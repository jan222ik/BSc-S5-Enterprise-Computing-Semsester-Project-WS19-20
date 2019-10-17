package at.fhv.itb17.s5.teamb.fxapp.viewnavigation;

import at.fhv.itb17.s5.teamb.fxapp.viewmodel.ViewModel;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.MenuPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.menuitem.MenuItemPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.menuitem.MenuItemView;
import javafx.beans.property.ReadOnlyDoubleProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;

public class MenuContentfulViewWrapper<T extends ViewModel> implements NavigationStackActions<T> {

    private static final Logger logger = LogManager.getLogger(MenuContentfulViewWrapper.class);

    private T viewModel;
    private Stack<ContentView<T>> navigationStack = new Stack<>();
    private String menuName;
    private String title;
    private MenuPresenter menuPresenter;
    private MenuItemPresenter menuItemPresenter;

    public MenuContentfulViewWrapper(ContentView<T> view, T viewModel, String menuName, String title, MenuPresenter menuPresenter) {
        this.viewModel = viewModel;
        if (viewModel == null) {
            logger.error("Model not present");
        }
        push(view);
        this.menuName = menuName;
        this.title = title;
        this.menuPresenter = menuPresenter;
    }

    public MenuItemView createMenuItemView(Runnable onClickAction, ReadOnlyDoubleProperty parentWidthProperty) {
        MenuItemView menuItemView = new MenuItemView();
        menuItemPresenter = (MenuItemPresenter) menuItemView.getPresenter();
        menuItemPresenter.setOnClickedAction(onClickAction);
        menuItemPresenter.setDisplayName(menuName);
        menuItemPresenter.setParentWidthProperty(parentWidthProperty);
        return menuItemView;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void popToRoot() {
        for (int i = navigationStack.size(); i > 1; i--) {
            popLast();
        }
    }

    @Override
    public void popLast() {
        ContentView<T> pop = navigationStack.pop();
        pop.preDestroy(viewModel);
    }

    @Override
    public void push(ContentView<T> view) {
        navigationStack.push(view);
        if (viewModel == null) {
            logger.error("Model not present: MEHTOD PUSH");
        }
        view.onCreate(viewModel, this);
    }

    @Override
    public void showTOS() {
        ContentView<T> contentView = navigationStack.peek();
        menuPresenter.setContentView(contentView.getView());
        contentView.onReturned(viewModel);
    }

    public void beforeMenuSwitch() {
        navigationStack.peek().beforeMenuSwitch(viewModel);
    }

    public void isCurrentMenuItem(boolean isCurrent) {
        menuItemPresenter.setSelected(isCurrent);
    }
}

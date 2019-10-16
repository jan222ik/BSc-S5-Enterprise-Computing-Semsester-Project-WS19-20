package at.fhv.itb17.s5.teamb.fxapp.views;

import at.fhv.itb17.s5.teamb.fxapp.views.menuitem.MenuItemPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.menuitem.MenuItemView;

import java.util.Stack;

public class MenuContentfulView {

    private String viewModel = ""; //TODO Create ViewModel holding data
    private Stack<ContentView> navigationStack = new Stack<>();
    private String menuName;
    private String title;

    public MenuContentfulView(ContentView view, String menuName, String title) {
        navigationStack.push(view);
        this.menuName = menuName;
        this.title = title;
    }

    public MenuItemView createMenuItemView(Runnable onClickAction) {
        MenuItemView menuItemView = new MenuItemView();
        MenuItemPresenter presenter = (MenuItemPresenter) menuItemView.getPresenter();
        presenter.setOnClickedAction(onClickAction);
        presenter.setDisplayName(menuName);
        return menuItemView;
    }

}

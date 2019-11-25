package at.fhv.itb17.s5.teamb.fxapp.views.content.cart;

import at.fhv.itb17.s5.teamb.fxapp.style.Style;
import at.fhv.itb17.s5.teamb.fxapp.util.NotificationsHelper;
import at.fhv.itb17.s5.teamb.fxapp.viewmodel.CartVM;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.ContentfulViewLifeCycle;
import at.fhv.itb17.s5.teamb.fxapp.viewnavigation.NavigationStackActions;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.cartitem.CartItemPresenter;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.cartitem.CartItemView;
import at.fhv.itb17.s5.teamb.fxapp.views.content.cart.confirmation.ConfirmationView;
import at.fhv.itb17.s5.teamb.fxapp.views.menu.ApplicationMenuViews;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CartPresenter implements ContentfulViewLifeCycle<CartVM> {

    @Inject
    private Style style;

    @FXML
    private Button clearBtn;
    @FXML
    private JFXListView<Parent> categoriesLV;
    @FXML
    private Label totalPriceL;
    @FXML
    private Label totalAmountL;
    @FXML
    private Button reserveBtn;
    @FXML
    private Button buyBtn;
    @FXML
    private Button backBtn;

    @Override
    public void onCreate(CartVM viewModel, NavigationStackActions<CartVM> navActions) {
        backBtn.setOnAction(e -> navActions.changeToMenuItem(ApplicationMenuViews.BROWSER_VIEW, true,  () -> NotificationsHelper.error("Internal Error", "Could not switch to menu item")));
        style.hoverBtn(backBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.SECONDARY().asPaint());
        buyBtn.setOnAction(e -> this.buyOrReserve(viewModel, true, navActions));
        style.hoverBtn(buyBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.PRIMARY().asPaint());
        reserveBtn.setOnAction(e -> this.buyOrReserve(viewModel, false, navActions));
        style.hoverBtn(reserveBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.PRIMARY().asPaint());
        clearBtn.setOnAction(e -> {
            viewModel.clear();
            render(viewModel);
        });
        style.hoverBtn(clearBtn, style.BACKGROUND().asBackground(), style.ON_BACKGROUND().asPaint(), style.BACKGROUND().asBackground(), style.ERROR().asPaint());
    }

    private void buyOrReserve(CartVM viewModel, boolean doBuy, NavigationStackActions<CartVM> navActions) {
        System.out.println("doBuy = " + doBuy);
        boolean successful = Optional.ofNullable(viewModel.book()).orElseGet(() -> {
            NotificationsHelper.error("Error", "Error during Booking");
            return false;
        });
        System.out.println("successful = " + successful);
        if (successful) {
            navActions.push(new ConfirmationView()).showTOS();
        } else {
            NotificationsHelper.warn("Booking Warning", "Some or all Events could not be booked");
        }
    }

    @Override
    public void onReturned(CartVM viewModel) {
        render(viewModel);
    }

    private void render(CartVM viewModel) {
        AtomicInteger totalPrice = new AtomicInteger();
        AtomicInteger totalAmount = new AtomicInteger();
        List<Parent> collect = viewModel.getTicketsSortedAfterEventAndOcc().stream().map(tick -> {
            CartItemView cartItemView = new CartItemView();
            CartItemPresenter presenter = (CartItemPresenter) cartItemView.getPresenter();
            presenter.setData(tick);
            totalAmount.getAndAdd(tick.size());
            totalPrice.getAndAdd(tick.get(0).getCat().getPriceInCent() * tick.size());
            return cartItemView.getView();
        }).collect(Collectors.toList());
        totalPriceL.setText((totalPrice.get() / 100) + "€");
        totalAmountL.setText(String.valueOf(totalAmount.get()));
        categoriesLV.getItems().setAll(collect);
    }
}

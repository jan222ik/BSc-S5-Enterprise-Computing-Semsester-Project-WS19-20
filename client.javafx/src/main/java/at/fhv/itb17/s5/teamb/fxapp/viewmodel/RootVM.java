package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.TicketDTO;

import java.util.List;

public class RootVM implements ViewModel {
    private SearchVM searchVM;
    private ResultVM resultVM;
    private CartVM cartVM;

    public SearchVM getSearchVM() {
        return searchVM;
    }

    public void setSearchVM(SearchVM searchVM) {
        this.searchVM = searchVM;
    }

    public ResultVM getResultVM() {
        return resultVM;
    }

    public void setResultVM(ResultVM resultVM) {
        this.resultVM = resultVM;
    }

    public String getGetSearchQuery() {
        return searchVM.getLatestSearchViewState().toQueryString();
    }

    public String getUsername() {
        return "Hugo"; //TODO use actual data
    }

    public void setCartVM(CartVM cartVM) {
        this.cartVM = cartVM;
    }

    public CartVM getCartVM() {
        return cartVM;
    }

    public void addTicketsForCart(List<TicketDTO> tickets) {
        cartVM.addTickets(tickets);
    }
}

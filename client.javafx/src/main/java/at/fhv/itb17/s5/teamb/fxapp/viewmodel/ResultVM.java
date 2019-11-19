package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;

import java.util.List;

public class ResultVM implements ViewModel {

    private SearchService searchService;
    private RootVM rootVM;

    public ResultVM(SearchService searchService, RootVM rootVM) {
        this.searchService = searchService;
        this.rootVM = rootVM;
    }

    public List<EventDTO> getSearchResults() {
        String getSearchQuery = rootVM.getGetSearchQuery();
        System.out.println("getSearchQuery = " + getSearchQuery);
        return searchService.searchFor(getSearchQuery);
    }

    public String getSearchString() {
        return rootVM.getGetSearchQuery();
    }

    public void setTicketsForBooking(List<TicketDTO> tickets) {
        rootVM.addTicketsForCart(tickets);
    }
}

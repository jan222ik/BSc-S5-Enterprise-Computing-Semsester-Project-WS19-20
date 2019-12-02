package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.dtos.EventDTO;
import at.fhv.itb17.s5.teamb.dtos.TicketDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ResultVM implements ViewModel {

    private static final Logger logger = LogManager.getLogger(ResultVM.class);

    private SearchService searchService;
    private RootVM rootVM;

    public ResultVM(SearchService searchService, RootVM rootVM) {
        this.searchService = searchService;
        this.rootVM = rootVM;
    }

    public List<EventDTO> getSearchResults() {
        String getSearchQuery = rootVM.getGetSearchQuery();
        logger.debug("getSearchQuery = {}", getSearchQuery);
        return searchService.searchFor(getSearchQuery);
    }

    public String getSearchString() {
        return rootVM.getGetSearchQuery();
    }

    public void setTicketsForBooking(List<TicketDTO> tickets) {
        rootVM.addTicketsForCart(tickets);
    }
}

package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.fxapp.data.PlaceholderDTO;
import at.fhv.itb17.s5.teamb.fxapp.data.SearchService;

import java.util.LinkedList;

public class ResultVM {

    private SearchService searchService;
    private RootVM rootVM;

    public ResultVM(SearchService searchService, RootVM rootVM) {
        this.searchService = searchService;
        this.rootVM = rootVM;
    }

    public LinkedList<PlaceholderDTO> getSearchResults() {
        return searchService.searchFor(rootVM.getGetSearchQuery());
    }
}

package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import java.time.LocalDate;

public class SearchVM implements ViewModel {
    private SearchViewState latestSearchViewState = null;

    public SearchVM() {

    }

    public SearchViewState getLatestSearchViewState() {
        return latestSearchViewState;
    }

    public void setLatestSearchViewState(SearchViewState latestSearchViewState) {
        this.latestSearchViewState = latestSearchViewState;
    }

    public static class SearchViewState {
        public boolean includeFrom;
        public LocalDate fromDate;
        public boolean includeTill;
        public LocalDate tillDate;
        public String event;
        public String artist;
        public int genre;
    }
}

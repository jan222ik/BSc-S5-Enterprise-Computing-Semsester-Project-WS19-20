package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

import at.fhv.itb17.s5.teamb.core.search.SearchParser;
import at.fhv.itb17.s5.teamb.persistence.search.SearchCategories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchVM implements ViewModel {
    private SearchViewData latestSearchViewState = null;

    public SearchViewData getLatestSearchViewState() {
        return latestSearchViewState;
    }

    public void setLatestSearchViewState(SearchViewData latestSearchViewData) {
        this.latestSearchViewState = latestSearchViewData;
    }

    public static class SearchViewData {
        private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        public boolean includeFrom;
        public LocalDate fromDate;
        public boolean includeTill;
        public LocalDate tillDate;
        public String event;
        public String artist;
        public int genre;
        public String genreValue;
        public String location;

        public String toQueryString() {
            //TODO Change type to SearchCategory.NAME.catIdf to work
            StringBuilder sb = new StringBuilder();
            if (includeFrom) {
                sb.append("-").append(SearchCategories.DATE_FROM).append(fromDate.format(format));
            }
            if (includeTill) {
                sb.append("-").append(SearchCategories.DATE_UNTIL).append(tillDate.format(format));
            }
            if (!event.isEmpty()) {
                sb.append("-").append(SearchCategories.EVENT_NAME).append(event);
            }
            if (!artist.isEmpty()) {
                sb.append("-").append(SearchCategories.ARTIST_NAME).append(artist);
            }
            if (!genreValue.isEmpty()) {
                sb.append("-").append(SearchCategories.GENRE).append(genreValue);
            }
            if(!location.isEmpty()) {
                sb.append("-").append(SearchCategories.LOCATION).append(location);
            }
            return sb.toString();
        }
    }
}

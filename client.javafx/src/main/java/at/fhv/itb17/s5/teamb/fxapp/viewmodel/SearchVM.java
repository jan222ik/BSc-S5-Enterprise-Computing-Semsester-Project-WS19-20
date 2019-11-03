package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

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
                sb.append("-").append(SearchCategories.DATE_FROM.getIdf()).append("=\"").append(fromDate.format(format)).append("\"");
            }
            if (includeTill) {
                sb.append("-").append(SearchCategories.DATE_UNTIL.getIdf()).append("=\"").append(tillDate.format(format)).append("\"");
            }
            if (!event.isEmpty()) {
                sb.append("-").append(SearchCategories.EVENT_NAME.getIdf()).append("=\"").append(event).append("\"");
            }
            if (!artist.isEmpty()) {
                sb.append("-").append(SearchCategories.ARTIST_NAME.getIdf()).append("=\"").append(artist).append("\"");
            }
            if (genreValue != null && !genreValue.isEmpty()) {
                sb.append("-").append(SearchCategories.GENRE.getIdf()).append("=\"").append(genreValue).append("\"");
            }
            if (location != null && !location.isEmpty()) {
                sb.append("-").append(SearchCategories.LOCATION.getIdf()).append("=\"").append(location).append("\"");
            }
            return sb.toString();
        }
    }
}

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
        private boolean includeFrom;
        private LocalDate fromDate;
        private boolean includeTill;
        private LocalDate tillDate;
        private String event;
        private String artist;
        private String genre;
        private String location;

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
            if (genre != null && !genre.isEmpty()) {
                sb.append("-").append(SearchCategories.GENRE.getIdf()).append("=\"").append(genre).append("\"");
            }
            if (location != null && !location.isEmpty()) {
                sb.append("-").append(SearchCategories.LOCATION.getIdf()).append("=\"").append(location).append("\"");
            }
            return sb.toString();
        }

        public boolean isIncludeFrom() {
            return includeFrom;
        }

        public void setIncludeFrom(boolean includeFrom) {
            this.includeFrom = includeFrom;
        }

        public LocalDate getFromDate() {
            return fromDate;
        }

        public void setFromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
        }

        public boolean isIncludeTill() {
            return includeTill;
        }

        public void setIncludeTill(boolean includeTill) {
            this.includeTill = includeTill;
        }

        public LocalDate getTillDate() {
            return tillDate;
        }

        public void setTillDate(LocalDate tillDate) {
            this.tillDate = tillDate;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}

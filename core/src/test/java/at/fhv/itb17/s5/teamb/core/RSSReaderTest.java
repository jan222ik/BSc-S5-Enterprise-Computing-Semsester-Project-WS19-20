package at.fhv.itb17.s5.teamb.core;

import at.fhv.itb17.s5.teamb.core.domain.msg.rss.RSSReader;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.FeedException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.*;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class RSSReaderTest {

    public static final String URL_FEED_1 = "https://homepages.fhv.at/mle2266/kultur";
    public static final int FEED_SIZE = 15;
    public static final String FIRST_ENTRY_AUTHOR = "ORF";
    public static final String FIRST_ENTRY_DESCRIPTION = "Am 4. Dezember ab 22.30 Uhr in ORF 2, danach „WELTjournal +: Abgeholzt – Europas bedrohte Urwälder“";
    public static final Date FIRST_ENTRY_PUBLISHED = Date.from(LocalDateTime.of(2019,12,3,17,10,4).toInstant(ZoneOffset.ofHours(1)));

    RSSReader rssReader;

    @BeforeEach
    public void initReader() throws IOException, FeedException {
        rssReader = new RSSReader(URL_FEED_1);
    }

    @Test
    @DisplayName("Test number of entries - Success")
    public void hasAllEntries() {
        List<SyndEntry> allEntries = (List<SyndEntry>) rssReader.getAllEntries();
        assertThat(allEntries.size(), Matchers.is(FEED_SIZE));
    }

    @Test
    @DisplayName("Test 1st entry author - Success")
    public void firstEntryAuthor(){
        SyndEntry entry = (SyndEntry) rssReader.getAllEntries().get(0);
        assertThat(entry.getAuthor(), Matchers.is(FIRST_ENTRY_AUTHOR));
    }

    @Test
    @DisplayName("Test 1st entry description - Success")
    public void firstEntryDescription(){
        SyndEntry entry = (SyndEntry) rssReader.getAllEntries().get(0);
        assertThat(entry.getDescription().getValue(), Matchers.is(FIRST_ENTRY_DESCRIPTION));
    }

    @Test
    @DisplayName("Test 1st entry publish date - Success")
    public void firstEntryDate(){
        SyndEntry entry = (SyndEntry) rssReader.getAllEntries().get(0);
        assertThat(entry.getPublishedDate(), Matchers.is(FIRST_ENTRY_PUBLISHED));
    }
}

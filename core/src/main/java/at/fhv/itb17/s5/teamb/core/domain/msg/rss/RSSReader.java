package at.fhv.itb17.s5.teamb.core.domain.msg.rss;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RSSReader {

    private SyndFeed feed;
    private XmlReader xmlReader;

    public static void main(String[] args) throws IOException, FeedException {
        RSSReader rssReader = new RSSReader("https://www.ots.at/rss/kultur");
        List< com.sun.syndication.feed.synd.SyndEntryImpl> allEntries = (List<SyndEntryImpl>) rssReader.getAllEntries();
        System.out.println((long) allEntries.size());
        allEntries.forEach(e -> {
            System.out.println("Title{" + e.getTitle() + "}");
            System.out.println("Desc{" + e.getDescription().getValue() + "}");
            System.out.println();
            //System.out.println(e.getTitle());
        });
    }

    public RSSReader(String url) throws IOException, FeedException {
        URL feedSource = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        xmlReader = new XmlReader(feedSource);
        feed = input.build(xmlReader);
    }

    public List<?> getAllEntries() {
        return feed.getEntries();
    }

    public void close() {
        try {
            xmlReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

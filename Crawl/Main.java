import java.io.IOException;

import crawl.HistoricalDynastyCrawl;
import crawl.HistoricalEventCrawl;
import crawl.HistoricalFestivalCrawl;
import crawl.HistoricalKing2Crawl;
import crawl.HistoricalPersonCrawl;
import crawl.HistoricalSiteCrawl;

public class Main {
    public static void main(String[] args) throws IOException {
        HistoricalKing2Crawl.KingCrawl_Combine();
        HistoricalPersonCrawl.PersonCrawl();
        HistoricalDynastyCrawl.DynastyCrawl();
        HistoricalEventCrawl.EventCrawl();
        HistoricalFestivalCrawl.FestivalCrawl();
        HistoricalSiteCrawl.SiteCrawl();
    }
}

package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.File;
import java.io.FileWriter;

public class CrawlerMain {
    public static void main(String[] args) throws Exception {

        File crawlStorage = new File(CrawlerConstants.STORE);
        CrawlConfig crawlConfig = new CrawlConfig();
        crawlConfig.setCrawlStorageFolder(crawlStorage.getAbsolutePath());

        crawlConfig.setMaxDepthOfCrawling(CrawlerConstants.MAX_DEPTH); //set the depth to two
        crawlConfig.setMaxOutgoingLinksToFollow(CrawlerConstants.MAX_OUTGOING_LINKS_TO_FOLLOW);
        crawlConfig.setPolitenessDelay(CrawlerConstants.POLITENESS_DELAY);
        crawlConfig.setIncludeHttpsPages(CrawlerConstants.INCLUDE_HTTPS);

        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);


        CrawlController crawlController = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);
        crawlController.addSeed(CrawlerConstants.SEED_URL);


        File file = new File(CrawlerConstants.XML_FILE_PATH);
        file.createNewFile();

        CrawlerStatistics statistics = new CrawlerStatistics();

        try (FileWriter writer = new FileWriter(CrawlerConstants.XML_FILE_PATH)) {
            CrawlController.WebCrawlerFactory<CrawlerHTML> factory = () -> new CrawlerHTML(statistics, writer);
            crawlController.start(factory, CrawlerConstants.NO_OF_CRAWLERS);
        }


    }
}
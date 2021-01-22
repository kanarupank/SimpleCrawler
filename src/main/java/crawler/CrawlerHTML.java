package crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 */
public class CrawlerHTML extends WebCrawler {
    private final static Pattern EXCLUDED_FORMATS
            = Pattern.compile(".*(\\.(mp3|mp4|zip|gz|css|js|xml|gif|jpg|png|pdf))$");


    private static final String SPACE = " ";
    private static final String ANGLE_OPEN = "<";
    private static final String ANGLE_CLOSE = ">";
    private static final String FORWARD_SLASH = "/";
    private static final String NEW_LINE = "\n";

    public enum TAG {
        DOC, DOCNO;
    }


    private CrawlerStatistics crawlerStatistics;
    private FileWriter writer;

    public CrawlerHTML(CrawlerStatistics stats, FileWriter myWriter) {
        this.crawlerStatistics = stats;
        this.writer = myWriter;
    }


    @Override
    public void visit(Page page) {
//        String url = page.getWebURL().getURL();
//        String text = htmlParseData.getText();
//        String html = htmlParseData.getHtml();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            crawlerStatistics.incrementTotalLinksCount(links.size());
            crawlerStatistics.incrementProcessedPageCount();

            //formatting for JASSjr
            //code formatter off and using constants for better readability
            //formatter:off
            String write_this_text = ANGLE_OPEN + TAG.DOC + ANGLE_CLOSE +
                    SPACE +
                    ANGLE_OPEN + TAG.DOCNO + ANGLE_CLOSE +
                    SPACE +
                    crawlerStatistics.getPageCount() +
                    SPACE +
                    ANGLE_OPEN + FORWARD_SLASH + TAG.DOCNO + ANGLE_CLOSE +
                    SPACE +
                    title +
                    SPACE +
                    ANGLE_OPEN + FORWARD_SLASH + TAG.DOC + ANGLE_CLOSE +
                    NEW_LINE;
            //formatter:on
            try {
                writer.write(write_this_text);

            } catch (IOException e) {
            }


        }
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !EXCLUDED_FORMATS.matcher(urlString).matches()
                && urlString.startsWith(CrawlerConstants.SEED_URL);
    }
}

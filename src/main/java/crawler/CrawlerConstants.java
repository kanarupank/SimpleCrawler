package crawler;

/**
 * Author: Kanarupan
 * Localizing the Configs and Common constants shared by multiple files
 */
public class CrawlerConstants {
    public static final String SEED_URL = "https://www.bbc.com/sport";
    public static final int NO_OF_CRAWLERS = 20;
    public static final int MAX_OUTGOING_LINKS_TO_FOLLOW = 2000;
    public static final int MAX_DEPTH = 2;
    public static final String XML_FILE_PATH = "data/bbc_sport.xml";
    public static final String STORE = "data";
    public static final int POLITENESS_DELAY = 300; //in milli seconds, 200 is the default
    public static final boolean INCLUDE_HTTPS = false;

}

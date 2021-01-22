package crawler;

public class CrawlerStatistics {

    private int noOfLinks = 0;
    private int noOfProcessedPages = 0;

    public void incrementProcessedPageCount() {
        noOfProcessedPages++;
    }

    public void incrementTotalLinksCount(int noOfLinks) {
        this.noOfLinks += noOfLinks;
    }

    public int getPageCount() {
        return noOfProcessedPages;
    }

    public int getNoOfLinks() {
        return noOfLinks;
    }
}
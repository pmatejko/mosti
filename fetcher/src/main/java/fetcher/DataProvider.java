package fetcher;

import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;

public enum DataProvider {
    TWITTER_API(new TwitterAPIFetcher(), 1000 * 60 * 10),
    NEWS_API(new NewsAPIFetcher(), 1000 * 60 * 15);

    /*******************************************************/

    private final Fetcher fetcher;
    private final long millisecondInterval;


    DataProvider(Fetcher fetcher, long millisecondInterval) {
        this.fetcher = fetcher;
        this.millisecondInterval = millisecondInterval;
    }


    public long getMillisecondInterval() {
        return millisecondInterval;
    }

    public Fetcher getFetcher() {
        return fetcher;
    }
}

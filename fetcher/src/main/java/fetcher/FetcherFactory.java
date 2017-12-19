package fetcher;

import exceptions.UnsupportedDataProviderException;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;
import model.DataProvider;

public class FetcherFactory {

    public Fetcher createFetcher(DataProvider dataProvider) {
        switch (dataProvider) {
            case NEWS_API:
                return new NewsAPIFetcher();
            case TWITTER_API:
                return new TwitterAPIFetcher();
            default:
                throw new UnsupportedDataProviderException();
        }
    }
}

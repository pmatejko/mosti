package guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import exceptions.UnsupportedDataProviderException;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;
import interfaces.FetcherProvider;
import model.DataProvider;

public class GuiceFetcherProvider implements FetcherProvider {
    @Inject private Provider<NewsAPIFetcher> newsAPIFetcherProvider;
    @Inject private Provider<TwitterAPIFetcher> twitterAPIFetcherProvider;

    public Fetcher getFetcher(DataProvider dataProvider) {
        switch (dataProvider) {
            case NEWS_API:
                return newsAPIFetcherProvider.get();
            case TWITTER_API:
                return twitterAPIFetcherProvider.get();
            default:
                throw new UnsupportedDataProviderException(dataProvider.name() + " is not implemented");
        }
    }
}

package guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import exceptions.UnsupportedDataProviderException;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;
import interfaces.FetcherProvider;
import model.DataProvider;

import java.util.Set;

public class GuiceFetcherProvider implements FetcherProvider {
    @Inject
    private Set<Fetcher> fetcherSet;

    public Fetcher getFetcher(DataProvider dataProvider) {
        for (Fetcher fetcher : fetcherSet) {
            if (fetcher.isCompatible(dataProvider))
                return fetcher;
        }

        throw new UnsupportedDataProviderException(dataProvider.name() + " is not implemented or not bound");
    }
}

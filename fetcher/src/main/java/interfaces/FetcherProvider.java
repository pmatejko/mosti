package interfaces;

import model.DataProvider;

public interface FetcherProvider {
    Fetcher getFetcher(DataProvider dataProvider);
}

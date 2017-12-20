package interfaces;

import model.DataProvider;

public interface IFetcherFactory {
    Fetcher createFetcher(DataProvider dataProvider);
}

package fetcher.impl;

import interfaces.Fetcher;
import model.DataProvider;

public abstract class AbstractFetcher implements Fetcher {
    private DataProvider dataProvider;

    public AbstractFetcher(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public boolean isCompatible(DataProvider dataProvider) {
        return this.dataProvider == dataProvider;
    }
}

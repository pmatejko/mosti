package fetcher;

import interfaces.Fetcher;

import java.util.List;

public abstract class NewsFetcher<T, U> implements Fetcher<List<T>, List<U>> {

    @Override
    public abstract List<U> fetch(List<T> arg);
}

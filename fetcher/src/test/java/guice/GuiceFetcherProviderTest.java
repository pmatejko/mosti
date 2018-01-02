package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provider;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;
import model.DataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class GuiceFetcherProviderTest  {

    @org.mockito.InjectMocks
    private GuiceFetcherProvider guiceFetcherProvider;

    @org.mockito.Mock
    private Provider newsAPIFetcherProvider;
    @org.mockito.Mock
    private Provider twitterAPIFetcherProvider;


    @Before
    public void setUp() throws Exception {
        NewsAPIFetcher newsAPIFetcher = Mockito.mock(NewsAPIFetcher.class);
        TwitterAPIFetcher twitterAPIFetcher = Mockito.mock(TwitterAPIFetcher.class);
        Mockito.when(newsAPIFetcherProvider.get()).thenReturn(newsAPIFetcher);
        Mockito.when(twitterAPIFetcherProvider.get()).thenReturn(twitterAPIFetcher);

        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //bind
            }
        });

    }


    @Test
    public void getFetcherTest() {

        Fetcher newsApiFetcher = guiceFetcherProvider.getFetcher(DataProvider.NEWS_API);
        assertTrue(newsApiFetcher instanceof NewsAPIFetcher);

        Fetcher twitterFetcher = guiceFetcherProvider.getFetcher(DataProvider.TWITTER_API);
        assertTrue(twitterFetcher instanceof TwitterAPIFetcher);

    }

}
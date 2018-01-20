package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import fetcher.impl.AbstractFetcher;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.Fetcher;
import model.DataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GuiceFetcherProviderTest  {

    @org.mockito.Mock
    private NewsAPIFetcher newsAPIFetcher;

    @org.mockito.Mock
    private TwitterAPIFetcher twitterAPIFetcher;

    @org.mockito.InjectMocks
    private GuiceFetcherProvider guiceFetcherProvider;

    @org.mockito.Mock
    private Set<Fetcher> fetcherSet;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this.getClass());

        when(fetcherSet.iterator()).thenAnswer(new Answer<Iterator<AbstractFetcher>>() {
            @Override
            public Iterator<AbstractFetcher> answer(InvocationOnMock invocation) throws Throwable {
                return Arrays.asList(newsAPIFetcher, twitterAPIFetcher).iterator();
            }
        });

        when(newsAPIFetcher.isCompatible(DataProvider.NEWS_API)).thenReturn(true);
        when(twitterAPIFetcher.isCompatible(DataProvider.TWITTER_API)).thenReturn(true);

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
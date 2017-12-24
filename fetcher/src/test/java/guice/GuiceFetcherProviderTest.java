package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provider;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiceFetcherProviderTest {
    public final class FakeNewsAPIFetcherProvider extends MockUp<Provider<NewsAPIFetcher>> {
        @Mock
        public NewsAPIFetcher get() {
            return new NewsAPIFetcher(null);

        }
    }

    public final class FakeTwitterAPIProvider extends MockUp<Provider<TwitterAPIFetcher>> {
        @Mock
        public TwitterAPIFetcher get() {
            return new TwitterAPIFetcher(null);
        }
    }

    private GuiceFetcherProvider guiceFetcherProvider;

    @Before
    public void setUp() throws Exception {
        guiceFetcherProvider = new GuiceFetcherProvider();
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //bind
            }
        });
    }

    @Test
    public void getFetcher() throws Exception {
    }

}
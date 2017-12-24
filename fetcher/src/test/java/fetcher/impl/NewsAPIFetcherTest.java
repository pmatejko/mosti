package fetcher.impl;

import exceptions.FetchingException;
import model.DataProvider;
import model.News;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import model.Preferences;


public class NewsAPIFetcherTest {
    private NewsAPIFetcher newsAPIFetcher;

    @Before
    public void setUp() throws Exception {
//        newsAPIFetcher = new NewsAPIFetcher();
    }

    @Test
    public void fetchTest() throws IOException {
        Preferences preferences = new Preferences("bitcoin", "bbc", DataProvider.NEWS_API);
        try {
            List<News> news = newsAPIFetcher.fetch(preferences);
        } catch (FetchingException e) {
            e.printStackTrace();
        }

    }
}
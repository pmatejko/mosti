package fetcher.impl;

import fetcher.impl.connectors.NewsAPIConnector;
import interfaces.PropertiesManager;
import model.News;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import model.Preferences;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.json.*;

import static org.junit.Assert.assertEquals;


@RunWith(PowerMockRunner.class)
@PrepareForTest({NewsAPIConnector.class, NewsAPIFetcher.class})
public class NewsAPIFetcherTest {

    @org.mockito.Mock
    private PropertiesManager propertiesManager;

    @org.mockito.Mock
    private Preferences preferences;

    @org.mockito.Mock
    private NewsAPIConnector newsAPIConnector;


    private NewsAPIFetcher newsAPIFetcher;


    @Before
    public void setUp() throws Exception {

        Mockito.when(preferences.getKeyword()).thenReturn("audi");
        Mockito.when(preferences.getNewsSource()).thenReturn("Sport");

        PowerMockito.whenNew(NewsAPIConnector.class).withArguments(Mockito.any()).thenReturn(newsAPIConnector);
        newsAPIFetcher = new NewsAPIFetcher(propertiesManager);

        String data = "[{\"source\":{\"id\":null,\"name\":\"Flickr.com\"},\"author\":\"Dave Pinter\",\"title\":\"Audi\",\"description\":\"Explore Dave Pinter's photos on Flickr. Dave Pinter has uploaded 38462 photos to Flickr.\",\"url\":\"https://www.flickr.com/photos/davepinter/38198189512\",\"urlToImage\":\"https://c1.staticflickr.com/5/4473/38198189512_a888c0181f_b.jpg\",\"publishedAt\":\"2017-11-07T02:52:42Z\"},{\"source\":{\"id\":null,\"name\":\"Deviantart.com\"},\"author\":null,\"title\":\"audi\",\"description\":\"\",\"url\":\"https://travisty187.deviantart.com/art/audi-724147013\",\"urlToImage\":\"https://img00.deviantart.net/b543/i/2018/006/7/c/audi_by_travisty187-dbz4zet.jpg\",\"publishedAt\":\"2018-01-06T18:07:11Z\"}]";

        BufferedReader in = new BufferedReader(new StringReader(data));
        JsonReader jsonReader = Json.createReader(in);
        JsonArray array = jsonReader.readArray();
        jsonReader.close();

        Mockito.when(newsAPIConnector.getData(preferences)).thenReturn(array);
    }


    @Test
    public void fetchTest() throws Exception {

        String expectedResult1 = "News Source: Sport, Keyword: audi\n" +
                "https://www.flickr.com/photos/davepinter/38198189512\n" +
                "2017-11-07 03:52:42.0\n" +
                "Audi Explore Dave Pinter's photos on Flickr. Dave Pinter has uploaded 38462 photos to Flickr.";
        String expectedResult2 = "News Source: Sport, Keyword: audi\n" +
                "https://travisty187.deviantart.com/art/audi-724147013\n" +
                "2018-01-06 19:07:11.0\n" +
                "audi ";

        List<News> news = newsAPIFetcher.fetch(preferences);

        assertEquals(news.get(0).toString(), expectedResult1);
        assertEquals(news.get(1).toString(), expectedResult2);

    }


}


package fetcher.impl;

import interfaces.PropertiesManager;
import model.News;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import model.Preferences;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class NewsAPIFetcherTest {

    @org.mockito.Mock
    private PropertiesManager propertiesManager;

    @org.mockito.Mock
    private Preferences preferences;

    private NewsAPIFetcher newsAPIFetcher;

    private String testKey = "01f114ce59ea483ab988a537b92e1d01";

    @Before
    public void setUp() throws Exception {

        Mockito.when(propertiesManager.getProperty(ArgumentMatchers.any())).thenReturn(testKey);

        Mockito.when(preferences.getKeyword()).thenReturn("bitcoin");
        Mockito.when(preferences.getNewsSource()).thenReturn("bbc");

        newsAPIFetcher = new NewsAPIFetcher(propertiesManager);
    }

    private Object getMethodResult(String methodName, Class[] parametersClass, Object[] parameters )
            throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
    {

        Method method = NewsAPIFetcher.class.getDeclaredMethod(methodName, parametersClass);
        method.setAccessible(true);
        return method.invoke(newsAPIFetcher, parameters);

    }


    @Test
    public void buildQueryStringTest() throws Exception {

        String expectedResult =
                "https://newsapi.org/v2/everything?q=bitcoin&sources=bbc&apiKey=" + testKey;

        Class[] parametersClass = new Class[1];
        parametersClass[0] = Preferences.class;

        Object[] parameters = new Object[1];
        parameters[0] = preferences;

        String result = (String)getMethodResult("buildQueryString", parametersClass, parameters);

        assertEquals(expectedResult, result);

    }


    @Test
    public void fetchTest() throws IOException {

        try {
            List<News> news = newsAPIFetcher.fetch(preferences);
        } catch (exceptions.FetchingException e) {
            e.printStackTrace();
        }

    }


}
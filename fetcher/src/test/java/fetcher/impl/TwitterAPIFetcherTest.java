package fetcher.impl;

import interfaces.PropertiesManager;
import model.DataProvider;
import model.News;
import model.Preferences;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class, URLConnection.class})
public class TwitterAPIFetcherTest {

    @org.mockito.Mock
    private PropertiesManager propertiesManager;

    @org.mockito.Mock
    private Preferences preferences;

    private TwitterAPIFetcher twitterAPIFetcher;


    @Before
    public void setUp() throws Exception {
        String testApiKey = "AyhoUctAu2UbxiniP7IZ2Kw6E";
        String testApiSecret = "C70q6DDjwv9VMHLHpWgWdijbcZPbjyJ4GnQU0CFJoUmlWvZ3iV";
        Mockito.when(propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_KEY)).thenReturn(testApiKey);
        Mockito.when(propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_SECRET)).thenReturn(testApiSecret);

        Mockito.when(preferences.getKeyword()).thenReturn("Audi");
        Mockito.when(preferences.getNewsSource()).thenReturn("audi");
        Mockito.when(preferences.getDataProvider()).thenReturn(DataProvider.TWITTER_API);

        twitterAPIFetcher = new TwitterAPIFetcher(propertiesManager);
    }


    private Object getMethodResult(String methodName, Class[] parametersClass, Object[] parameters )
        throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
    {

        Method method = TwitterAPIFetcher.class.getDeclaredMethod(methodName, parametersClass);
        method.setAccessible(true);
        return method.invoke(twitterAPIFetcher, parameters);

    }


    @Test
    public void buildQueryStringTest() throws Exception {

        String expectedResult =
                "https://api.twitter.com/1.1/search/tweets.json?q=Audi+from%3Aaudi";

        Class[] parametersClass = new Class[1];
        parametersClass[0] = Preferences.class;

        Object[] parameters = new Object[1];
        parameters[0] = preferences;

        String result = (String)getMethodResult("buildQueryString", parametersClass, parameters);

        assertEquals(expectedResult, result);
    }


    @Test
    public void createQueryConnectionTest() throws Exception {

        Class[] parametersClass = new Class[1];
        parametersClass[0] = String.class;

        String url = "https://api.twitter.com/1.1/search/tweets.json?q=Audi+from%3Aaudi";
        Object[] parameters = new Object[1];
        parameters[0] = url;

        try {
            URL u = PowerMockito.mock(URL.class);
            PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(u);
            HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
            PowerMockito.when(u.openConnection()).thenReturn(huc);

        }
        catch(Exception e){
            System.out.println("Exception in tests code.");
        }

        HttpURLConnection result = (HttpURLConnection) getMethodResult("createQueryConnection",
                parametersClass, parameters);

    }


    @Test
    public void createEncodedCredentialsTest() throws Exception {
        String expectedResult = "QXlob1VjdEF1MlVieGluaVA3SVoyS3c2RTpDNzBxNkREand2OVZNSExIcFdnV2RpamJjWlBianlKNEduUVUw" +
                "Q0ZKb1VtbFd2WjNpVg==";

        Class[] parametersClass = new Class[2];
        parametersClass[0] = String.class;
        parametersClass[1] = String.class;

        Object[] parameters = new Object[2];
        parameters[0] = propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_KEY);
        parameters[1] = propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_SECRET);

        String result = (String)getMethodResult("createEncodedCredentials", parametersClass, parameters);

        assertEquals(expectedResult, result);

    }


    @Test
    public void createAccessTokenConnectionTest() throws Exception {
        Class[] parametersClass = new Class[1];
        parametersClass[0] = String.class;

        Object[] parameters = new Object[1];
        parameters[0] = "test";

        try {
            URL u = PowerMockito.mock(URL.class);
            PowerMockito.whenNew(URL.class).withArguments("https://api.twitter.com/oauth2/token")
                    .thenReturn(u);
            HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
            PowerMockito.when(u.openConnection()).thenReturn(huc);
            OutputStream os = PowerMockito.mock(OutputStream.class);
            PowerMockito.when(huc.getOutputStream()).thenReturn(os);

        }
        catch(Exception e){
            System.out.println("Exception in tests code.");
        }

        HttpURLConnection result = (HttpURLConnection) getMethodResult("createAccessTokenConnection",
                parametersClass, parameters);

    }


    @Test
    public void fetchTest() throws IOException {

        try {
            List<News> news = twitterAPIFetcher.fetch(preferences);
        } catch (exceptions.FetchingException e) {
            e.printStackTrace();
        }

    }

}

package fetcher.impl.connectors;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.FetchingException;
import interfaces.Connector;
import interfaces.PropertiesManager;
import model.News;
import model.Preferences;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

@Singleton
public class NewsAPIConnector implements Connector {

    private static final String API_URL = "https://newsapi.org/v2/everything?";
    private final String API_KEY;

    @Inject
    public NewsAPIConnector(PropertiesManager propertiesManager){
        API_KEY = propertiesManager.getProperty(PropertiesManager.Keys.NEWS_API_KEY);
    }


    public JsonArray getData(Preferences preferences) throws FetchingException {
        try {
            String queryString = buildQueryString(preferences);

            URL apiQueryUrl = new URL(queryString);
            BufferedReader in = new BufferedReader(new InputStreamReader(apiQueryUrl.openStream()));
            JsonReader reader = Json.createReader(in);
            JsonObject page = reader.readObject();
            reader.close();

            return page.getJsonArray("articles");

        } catch (IOException e) {
            throw new FetchingException(e);
        }
    }


    private String buildQueryString(Preferences preferences) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(API_URL);

        if (preferences.getKeyword() != null) {
            stringBuilder.append("q=");
            stringBuilder.append(URLEncoder.encode(preferences.getKeyword(), UTF_8.toString()));
        }

        if (preferences.getKeyword() != null && preferences.getNewsSource() != null) {
            stringBuilder.append("&");
        }

        if (preferences.getNewsSource() != null) {
            stringBuilder.append("domains=");
            stringBuilder.append(URLEncoder.encode(preferences.getNewsSource(), UTF_8.toString()));
        }

        stringBuilder.append("&apiKey=");
        stringBuilder.append(API_KEY);

        return stringBuilder.toString();
    }


}

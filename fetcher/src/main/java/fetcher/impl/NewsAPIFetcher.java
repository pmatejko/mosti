package fetcher.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.FetchingException;
import interfaces.Fetcher;
import interfaces.PropertiesManager;
import model.News;
import model.Preferences;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Singleton
public class NewsAPIFetcher implements Fetcher {
    private static final String API_URL = "https://newsapi.org/v2/everything?";

    private final String API_KEY;


    @Inject
    public NewsAPIFetcher(PropertiesManager propertiesManager) {
        API_KEY = propertiesManager.getProperty(PropertiesManager.Keys.NEWS_API_KEY);
    }


    @Override
    public List<News> fetch(Preferences preferences) throws FetchingException {
        try {
            String queryString = buildQueryString(preferences);

            URL apiQueryUrl = new URL(queryString);
            BufferedReader in = new BufferedReader(new InputStreamReader(apiQueryUrl.openStream()));
            JsonReader reader = Json.createReader(in);
            JsonObject page = reader.readObject();
            reader.close();

            JsonArray jsonArticles = page.getJsonArray("articles");
            return parseJsonArticleArray(jsonArticles, preferences);
        } catch (IOException e) {
            throw new FetchingException(e);
        }
    }

    private String buildQueryString(Preferences preferences) {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(API_URL);

        if (preferences.getKeyword() != null) {
            stringBuilder.append("q=");
            stringBuilder.append(preferences.getKeyword());
        }

        if (preferences.getKeyword() != null && preferences.getNewsSource() != null) {
            stringBuilder.append("&");
        }

        if (preferences.getNewsSource() != null) {
            stringBuilder.append("sources=");
            stringBuilder.append(preferences.getNewsSource());
        }

        stringBuilder.append("&apiKey=");
        stringBuilder.append(API_KEY);

        return stringBuilder.toString();
    }

    private List<News> parseJsonArticleArray(JsonArray jsonArticles, Preferences preferences) {
        List<News> articles = new LinkedList<>();

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String url = jsonArticle.getString("url");
                String content = jsonArticle.getString("title") + " " + jsonArticle.getString("description");
                Date timestamp = Date.from(Instant.from(ISO_DATE_TIME.parse(jsonArticle.getString("publishedAt"))));

                articles.add(new News(preferences, url, content, new Timestamp(System.currentTimeMillis())));
            }
        }

        return articles;
    }
}

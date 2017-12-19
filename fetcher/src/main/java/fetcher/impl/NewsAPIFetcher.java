package fetcher.impl;

import dto.SubscriptionDTO;
import interfaces.Fetcher;
import model.News;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class NewsAPIFetcher implements Fetcher {
    private static final String apiUrl = "https://newsapi.org/v2/everything?";
    private static final String apiKey = "apiKey=cc36659c4c784994a77282ce4e4dc1ed";

    @Override
    public List<News> fetch(SubscriptionDTO subscription) throws IOException {
        String queryString = buildQueryString(subscription);

        URL apiQueryUrl = new URL(queryString);
        BufferedReader in = new BufferedReader(new InputStreamReader(apiQueryUrl.openStream()));
        JsonReader reader = Json.createReader(in);
        JsonObject page = reader.readObject();

        reader.close();
        in.close();

        JsonArray jsonArticles = page.getJsonArray("articles");
        return parseJsonArticleArray(jsonArticles, subscription);
    }

    private String buildQueryString(SubscriptionDTO subscription) {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(apiUrl);

        if (subscription.getKeyword() != null) {
            stringBuilder.append("q=");
            stringBuilder.append(subscription.getKeyword());
        }

        if (subscription.getKeyword() != null && subscription.getNewsSource() != null) {
            stringBuilder.append("&");
        }

        if (subscription.getNewsSource() != null) {
            stringBuilder.append("sources=");
            stringBuilder.append(subscription.getNewsSource());
        }

        stringBuilder.append("&");
        stringBuilder.append(apiKey);

        return stringBuilder.toString();
    }

    private List<News> parseJsonArticleArray(JsonArray jsonArticles, SubscriptionDTO subscription) {
        List<News> articles = new LinkedList<>();

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String url = jsonArticle.getString("url");
                String content = jsonArticle.getString("title") + " " + jsonArticle.getString("description");
                Date timestamp = Date.from(Instant.from(ISO_DATE_TIME.parse(jsonArticle.getString("publishedAt"))));

                articles.add(new News(subscription.getNewsSource(), subscription.getKeyword(), url, content, timestamp));
            }
        }

        return articles;
    }
}

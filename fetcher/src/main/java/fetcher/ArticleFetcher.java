package fetcher;

import entities.NewsSiteSubscription;
import interfaces.Fetcher;
import model.Article;

import javax.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class ArticleFetcher implements Fetcher<List<NewsSiteSubscription>, Article> {
    private static final String apiUrl = "https://newsapi.org/v2/everything?";
    private static final String apiKey = "apiKey=cc36659c4c784994a77282ce4e4dc1ed";

    @Override
    public List<Article> fetch(List<NewsSiteSubscription> subscriptions) throws IOException {
        List<Article> articles = new LinkedList<>();

        for (NewsSiteSubscription subscription : subscriptions) {
            String queryString = buildQueryString(subscription);

            URL apiQueryUrl = new URL(queryString);
            BufferedReader in = new BufferedReader(new InputStreamReader(apiQueryUrl.openStream()));
            JsonReader reader = Json.createReader(in);
            JsonObject page = reader.readObject();

            JsonArray jsonArticles = page.getJsonArray("articles");
            articles.addAll(parseJsonArticleArray(jsonArticles));

            reader.close();
            in.close();
        }

        return articles;
    }

    private String buildQueryString(NewsSiteSubscription subscription) {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(apiUrl);

        if (subscription.getKeywords() != null) {
            stringBuilder.append("q=");
            stringBuilder.append(subscription.getKeywords());
        }

        if (subscription.getKeywords() != null && subscription.getNewsSites() != null) {
            stringBuilder.append("&");
        }

        if (subscription.getNewsSites() != null) {
            stringBuilder.append("sources=");
            stringBuilder.append(subscription.getNewsSites());
        }

        stringBuilder.append("&");
        stringBuilder.append(apiKey);

        return stringBuilder.toString();
    }

    private List<Article> parseJsonArticleArray(JsonArray jsonArticles) {
        List<Article> articles = new LinkedList<>();

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String title = jsonArticle.getString("author");
                String newsSite = jsonArticle.getJsonObject("source").getString("name");
                String url = jsonArticle.getString("url");
                String content = jsonArticle.getString("description");
                Date timestamp = Date.from(Instant.from(ISO_DATE_TIME.parse(jsonArticle.getString("publishedAt"))));

                articles.add(new Article(title, newsSite, url, content, timestamp));
            }
        }

        return articles;
    }

}

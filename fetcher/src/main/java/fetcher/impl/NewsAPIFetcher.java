package fetcher.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.FetchingException;
import fetcher.impl.connectors.NewsAPIConnector;
import interfaces.PropertiesManager;
import model.DataProvider;
import model.News;
import model.Preferences;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Singleton
public class NewsAPIFetcher extends AbstractFetcher {


    private NewsAPIConnector newsAPIConnector;


    @Inject
    public NewsAPIFetcher(PropertiesManager propertiesManager) {
        super(DataProvider.NEWS_API);
        newsAPIConnector = new NewsAPIConnector(propertiesManager);

    }


    @Override
    public List<News> fetch(Preferences preferences) throws FetchingException {

        JsonArray jsonArticles = newsAPIConnector.getData(preferences);

        return parseJsonArticleArray(jsonArticles, preferences);

    }


    private List<News> parseJsonArticleArray(JsonArray jsonArticles, Preferences preferences) {
        List<News> articles = new LinkedList<>();

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String url = jsonArticle.getString("url");
                String content = jsonArticle.getString("title") + " " + jsonArticle.getString("description");
                Timestamp timestamp = Timestamp.from(Instant.from(ISO_DATE_TIME.parse(jsonArticle.getString("publishedAt"))));

                articles.add(new News(preferences, url, content, timestamp));
            }
        }

        return articles;
    }

}

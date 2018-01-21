package fetcher.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import exceptions.FetchingException;
import fetcher.impl.connectors.TwitterAPIConnector;
import interfaces.PropertiesManager;
import model.DataProvider;
import model.News;
import model.Preferences;

import javax.json.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Singleton
public class TwitterAPIFetcher extends AbstractFetcher {

    private static final String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";

    private TwitterAPIConnector twitterAPIConnector;


    @Inject
    public TwitterAPIFetcher(TwitterAPIConnector twitterAPIConnector) {
        super(DataProvider.TWITTER_API);
        this.twitterAPIConnector = twitterAPIConnector;
    }


    @Override
    public List<News> fetch(Preferences preferences) throws FetchingException {
        try {
            JsonArray jsonTweets = twitterAPIConnector.getData(preferences);
            return parseJsonTweetsArray(jsonTweets, preferences);
        } catch (ParseException e) {
            throw new FetchingException(e);
        }
    }


    private List<News> parseJsonTweetsArray(JsonArray jsonArticles, Preferences preferences) throws ParseException {
        List<News> tweets = new LinkedList<>();

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String url = "https://twitter.com/statuses/" + jsonArticle.getString("id_str");
                String content = jsonArticle.getString("text");
                Date date = new SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.ENGLISH)
                        .parse(jsonArticle.getString("created_at"));

                long time = date.getTime();
                tweets.add(new News(preferences, url, content, new Timestamp(time)));
            }
        }

        return tweets;
    }

}

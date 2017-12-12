package fetcher;

import entities.TwitterSubscription;
import model.Tweet;

import java.util.List;

public class TweetFetcher extends NewsFetcher<TwitterSubscription, Tweet> {
    private static final TweetFetcher INSTANCE = new TweetFetcher();

    private TweetFetcher() {}

    public static TweetFetcher getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Tweet> fetch(List<TwitterSubscription> subscriptions) {
        return null;
    }

}
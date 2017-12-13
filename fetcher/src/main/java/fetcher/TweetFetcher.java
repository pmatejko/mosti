package fetcher;

import entities.TwitterSubscription;
import interfaces.Fetcher;
import model.Tweet;

import java.util.List;

public class TweetFetcher implements Fetcher<List<TwitterSubscription>, Tweet> {

    @Override
    public List<Tweet> fetch(List<TwitterSubscription> subscriptions) {
        return null;
    }

}

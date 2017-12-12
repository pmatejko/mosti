package fetcher;

import entities.Article;
import entities.Tweet;
import entities.UserNews;
import entities.UserSubscription;
import io.reactivex.Observer;

import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    private Observer<UserNews> userNewsObserver;
    private UserSubscription subscription;


    public FetcherTask(Observer<UserNews> userNewsObserver,
                       UserSubscription subscription) {
        this.userNewsObserver = userNewsObserver;
        this.subscription = subscription;
    }


    @Override
    public void run() {
        List<Article> articles = ArticleFetcher.getInstance().fetch(subscription.getNewsSiteSubscriptions());
        List<Tweet> tweets = TweetFetcher.getInstance().fetch(subscription.getTwitterSubscriptions());

        UserNews userNews = new UserNews(subscription.getUserId(), articles, tweets);
        userNewsObserver.onNext(userNews);
    }

}

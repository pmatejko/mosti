package fetcher;

import entities.UserNews;
import entities.UserSubscription;
import io.reactivex.Observer;
import model.Article;
import model.News;
import model.Tweet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    private final NewsFetcher newsFetcher;
    private Observer<UserNews> userNewsObserver;
    private UserSubscription subscription;


    public FetcherTask(Observer<UserNews> userNewsObserver,
                       UserSubscription subscription) {
        this.userNewsObserver = userNewsObserver;
        this.subscription = subscription;

        this.newsFetcher = new NewsFetcher();
    }


    @Override
    public void run() {
        try {
            List<News> news = newsFetcher.fetch(subscription);
            UserNews userNews = new UserNews(subscription.getUserId(), news);

            userNewsObserver.onNext(userNews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

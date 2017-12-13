package fetcher;

import entities.UserSubscription;
import interfaces.Fetcher;
import model.Article;
import model.News;
import model.Tweet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NewsFetcher implements Fetcher<UserSubscription, News> {
    private ArticleFetcher articleFetcher = new ArticleFetcher();
    private TweetFetcher tweetFetcher = new TweetFetcher();

    @Override
    public List<News> fetch(UserSubscription userSubscription) throws IOException {
        List<Article> articles = articleFetcher.fetch(userSubscription.getNewsSiteSubscriptions());
        List<Tweet> tweets = tweetFetcher.fetch(userSubscription.getTwitterSubscriptions());

        List<News> news = new LinkedList<>();
        news.addAll(articles);
        news.addAll(tweets);

        return news;
    }
}

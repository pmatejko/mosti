package fetcher;

import entities.Article;
import entities.NewsSiteSubscription;

import java.util.List;

public class ArticleFetcher extends NewsFetcher<NewsSiteSubscription, Article> {
    private static final ArticleFetcher INSTANCE = new ArticleFetcher();

    private ArticleFetcher() {}

    public static ArticleFetcher getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Article> fetch(List<NewsSiteSubscription> arg) {
        return null;
    }

}

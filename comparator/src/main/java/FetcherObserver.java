import entities.UserNews;
import interfaces.IComparator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import model.Article;
import model.Tweet;

import java.util.List;
import java.util.stream.Collectors;

public class FetcherObserver implements Observer<UserNews>{
    private IComparator<Article> articleIComparator;  // will be injected by guice
    private IComparator<Tweet>   tweetIComparator;   // will be injected by guice

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(UserNews userNews) {
        List<Article> articleList = userNews.getArticleList()
                .stream()
                .filter(articleIComparator::compareIfNew)
                .collect(Collectors.toList());
        List<Tweet> tweetList = userNews.getTweetList()
                .stream()
                .filter(tweetIComparator::compareIfNew)
                .collect(Collectors.toList());
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

import dao.INewsDao;
import entities.UserNews;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import model.Article;
import model.News;
import model.Tweet;

import java.util.List;
import java.util.stream.Collectors;

public class FetcherObserver implements Observer<UserNews>{
    private INewsDao newsDao; //guice

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(UserNews userNews) {
        List<News> newsList = userNews.getNewsList()
                .stream()
                .filter(newsDao::isNew)
                .collect(Collectors.toList());

    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

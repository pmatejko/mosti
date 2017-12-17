import com.google.inject.Inject;
import dao.NewsDao;
import entities.NewsDTO;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FetcherObserver implements Observer<NewsDTO>{
    @Inject
    private NewsDao newsDao;

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(NewsDTO newsDTO) {

    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

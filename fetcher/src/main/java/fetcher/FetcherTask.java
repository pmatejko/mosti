package fetcher;

import dto.NewsDTO;
import dto.SubscriptionDTO;
import io.reactivex.Observer;
import model.News;
import model.Preferences;

import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    private final Observer<NewsDTO> newsObserver;
    private final DataProvider dataProvider;
    private final SubscriptionDTO subscription;


    public FetcherTask(Observer<NewsDTO> newsObserver, Preferences preferences) {
        this.newsObserver = newsObserver;
        this.dataProvider = preferences.getDataProvider();
        this.subscription = new SubscriptionDTO(preferences);
    }


    @Override
    public void run() {
        List<News> newsList = dataProvider.fetch(subscription);

        NewsDTO newsDTO = new NewsDTO(newsList);
        newsObserver.onNext(newsDTO);
    }

}

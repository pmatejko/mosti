package fetcher;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import dto.NewsDTO;
import interfaces.Fetcher;
import interfaces.FetcherProvider;
import io.reactivex.Observer;
import model.News;
import model.Preferences;

import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    private final Observer<NewsDTO> newsObserver;
    private final FetcherProvider fetcherProvider;
    private final Preferences preferences;


    @Inject
    public FetcherTask(Observer<NewsDTO> newsObserver, FetcherProvider fetcherProvider,
                       @Assisted Preferences preferences) {
        this.newsObserver = newsObserver;
        this.fetcherProvider = fetcherProvider;
        this.preferences = preferences;
    }


    @Override
    public void run() {
        try {
            Fetcher fetcher = fetcherProvider.getFetcher(preferences.getDataProvider());
            List<News> newsList = fetcher.fetch(preferences);

            NewsDTO newsDTO = new NewsDTO(newsList);
            newsObserver.onNext(newsDTO);
        } catch (Exception e) {
            // TODO - for example retry after some time
            e.printStackTrace();
        }
    }
}

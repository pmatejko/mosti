package fetcher;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import dto.NewsDTO;
import exceptions.FetchingException;
import interfaces.Fetcher;
import interfaces.FetcherProvider;
import io.reactivex.Observer;
import model.News;
import model.Preferences;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FetcherRunnable implements Runnable {
    private final Observer<NewsDTO> newsObserver;
    private final FetcherProvider fetcherProvider;
    private final Preferences preferences;


    @Inject
    public FetcherRunnable(Observer<NewsDTO> newsObserver, FetcherProvider fetcherProvider,
                           @Assisted Preferences preferences) {
        this.newsObserver = newsObserver;
        this.fetcherProvider = fetcherProvider;
        this.preferences = preferences;
    }


    @Override
    public void run() {
        Fetcher fetcher = fetcherProvider.getFetcher(preferences.getDataProvider());
        List<News> newsList = fetcher.fetch(preferences);

        NewsDTO newsDTO = new NewsDTO(newsList);
        newsObserver.onNext(newsDTO);
    }
}

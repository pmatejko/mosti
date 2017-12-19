package fetcher;

import com.google.inject.Inject;
import dto.NewsDTO;
import interfaces.Fetcher;
import io.reactivex.Observer;
import model.News;
import model.Preferences;

import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    @Inject
    private Observer<NewsDTO> newsObserver;
    @Inject
    private FetcherFactory fetcherFactory;

    private final Preferences preferences;


    public FetcherTask(Preferences preferences) {
        this.preferences = preferences;
    }


    @Override
    public void run() {
        try {
            Fetcher fetcher = fetcherFactory.createFetcher(preferences.getDataProvider());
            List<News> newsList = fetcher.fetch(preferences);

            NewsDTO newsDTO = new NewsDTO(newsList);
            newsObserver.onNext(newsDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

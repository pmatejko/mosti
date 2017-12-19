package fetcher;

import Comparator.ComparatorManager;
import com.google.inject.Inject;
import dto.NewsDTO;
import model.News;
import model.Preferences;

import java.util.List;
import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    @Inject
    private ComparatorManager comparatorManager;

    private final Preferences preferences;


    public FetcherTask(Preferences preferences) {
        this.preferences = preferences;
    }


    @Override
    public void run() {
        try {
            List<News> newsList = preferences
                    .getDataProvider()
                    .getFetcher()
                    .fetch(preferences);

            NewsDTO newsDTO = new NewsDTO(newsList);
            comparatorManager.process(newsDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package fetcher;

import com.google.inject.Singleton;
import dto.NewsDTO;
import interfaces.FetchingManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import model.Preferences;

import java.util.*;

@Singleton
public class SubscriptionService implements FetchingManager<NewsDTO> {
    private final Observer<NewsDTO> newsObserver;
    private final Observable<NewsDTO> newsObservable;

    private final Timer timer = new Timer();
    private final Map<Long, FetcherTask> activeTasksMap = new HashMap<>();


    private SubscriptionService() {
        PublishSubject<NewsDTO> newsSubject = PublishSubject.create();
        newsObservable = newsSubject;
        newsObserver = newsSubject;
    }


    @Override
    public Observable<NewsDTO> getObservable() {
        return newsObservable;
    }

    public void addSubscription(Preferences preferences) {
        FetcherTask task = new FetcherTask(newsObserver, preferences);
        timer.schedule(task, 0, preferences.getDataProvider().getMillisecondInterval());
        activeTasksMap.put(preferences.getId(), task);
    }

    public void cancelSubscription(Preferences preferences) {
        FetcherTask task = activeTasksMap.get(preferences.getId());

        if (task != null) {
            task.cancel();
            activeTasksMap.remove(preferences.getId());
        }
    }

}

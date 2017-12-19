package fetcher;

import dto.NewsDTO;
import interfaces.FetchingManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import model.Preferences;

import java.util.*;

public class NewsFetchingManager implements FetchingManager<NewsDTO> {
    private final static NewsFetchingManager INSTANCE = new NewsFetchingManager();

    private final Observer<NewsDTO> newsObserver;
    private final Observable<NewsDTO> newsObservable;

    private final Timer timer = new Timer();
    private final Map<Long, FetcherTask> userActiveTasksMap = new HashMap<>();


    private NewsFetchingManager() {
        PublishSubject<NewsDTO> newsSubject = PublishSubject.create();
        newsObservable = newsSubject;
        newsObserver = newsSubject;
    }

    public static NewsFetchingManager getInstance() {
        return INSTANCE;
    }


    @Override
    public Observable<NewsDTO> getObservable() {
        return newsObservable;
    }

    public void addSubscription(Preferences preferences) {
        FetcherTask task = new FetcherTask(newsObserver, preferences);
        timer.schedule(task, 0, preferences.getDataProvider().getMillisecondInterval());
        userActiveTasksMap.put(preferences.getId(), task);
    }

    public void cancelSubscription(Preferences preferences) {
        FetcherTask task = userActiveTasksMap.get(preferences.getId());

        if (task != null) {
            task.cancel();
            userActiveTasksMap.remove(preferences.getId());
        }
    }

}

package fetcher;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dto.NewsDTO;
import interfaces.FetcherTaskFactory;
import interfaces.IFetchingManager;
import io.reactivex.Observable;
import model.Preferences;

import java.util.*;

@Singleton
public class FetchingManager implements IFetchingManager {
    @Inject
    private Observable<NewsDTO> newsObservable;
    @Inject
    private FetcherTaskFactory fetcherTaskFactory;

    private final Timer timer = new Timer();
    private final Map<Long, FetcherTask> activeTasksMap = new HashMap<>();


    @Override
    public Observable<NewsDTO> getNewsObservable() {
        return newsObservable;
    }

    public void addSubscription(Preferences preferences) {
        FetcherTask task = fetcherTaskFactory.create(preferences);
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

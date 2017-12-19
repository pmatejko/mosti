package fetcher;

import com.google.inject.Singleton;
import model.Preferences;

import java.util.*;

@Singleton
public class SubscriptionService {
    private final Timer timer = new Timer();
    private final Map<Long, FetcherTask> activeTasksMap = new HashMap<>();


    public void addSubscription(Preferences preferences) {
        FetcherTask task = new FetcherTask(preferences);
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

package fetcher;

import entities.UserNews;
import entities.UserSubscription;
import io.reactivex.Observer;

import java.util.*;

public class SubscriptionManager {
    private Timer timer = new Timer();
    private Map<Long, FetcherTask> userActiveTasksMap = new HashMap<>();
    private Observer<UserNews> userNewsObserver;


    public void addUserSubscription(UserSubscription subscription) {
        FetcherTask task = new FetcherTask(userNewsObserver, subscription);
        timer.schedule(task, 0, subscription.getInterval());
        userActiveTasksMap.put(subscription.getUserId(), task);
    }

    public void cancelUserSubscription(long userId) {
        FetcherTask task = userActiveTasksMap.get(userId);

        if (task != null) {
            task.cancel();
            userActiveTasksMap.remove(userId);
        }
    }
}

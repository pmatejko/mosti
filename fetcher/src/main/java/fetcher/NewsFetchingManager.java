package fetcher;

import entities.UserNews;
import entities.UserSubscription;
import interfaces.FetchingManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

import java.util.*;

public class NewsFetchingManager implements FetchingManager<UserNews> {
    private final static NewsFetchingManager INSTANCE = new NewsFetchingManager();

    private final Observer<UserNews> userNewsObserver;
    private final Observable<UserNews> userNewsObservable;

    private final Timer timer = new Timer();
    private final Map<Long, FetcherTask> userActiveTasksMap = new HashMap<>();


    private NewsFetchingManager() {
        PublishSubject<UserNews> userNewsSubject = PublishSubject.create();
        userNewsObservable = userNewsSubject;
        userNewsObserver = userNewsSubject;
    }

    public static NewsFetchingManager getInstance() {
        return INSTANCE;
    }


    @Override
    public Observable<UserNews> getObservable() {
        return userNewsObservable;
    }

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

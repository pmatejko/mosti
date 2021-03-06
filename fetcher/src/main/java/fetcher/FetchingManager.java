package fetcher;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.PreferencesDao;
import dto.NewsDTO;
import interfaces.FetcherRunnableFactory;
import interfaces.IFetchingManager;
import interfaces.SubscriptionManager;
import io.reactivex.Observable;
import model.Preferences;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public class FetchingManager implements IFetchingManager, SubscriptionManager {
    private Observable<NewsDTO> newsObservable;
    private FetcherRunnableFactory fetcherRunnableFactory;
    private ScheduledExecutorService scheduledExecutorService;
    private final Map<Long, ScheduledFuture<?>> activeRunnablesMap = new HashMap<>();

    @Inject
    public FetchingManager(Observable<NewsDTO> newsObservable, FetcherRunnableFactory fetcherRunnableFactory,
                           ScheduledExecutorService scheduledExecutorService, PreferencesDao preferencesDao) {
        this.newsObservable = newsObservable;
        this.fetcherRunnableFactory = fetcherRunnableFactory;
        this.scheduledExecutorService = scheduledExecutorService;

        addAllSubscriptions(preferencesDao.getAllPreferences());
    }


    @Override
    public Observable<NewsDTO> getNewsObservable() {
        return newsObservable;
    }

    public void addAllSubscriptions(List<Preferences> preferencesList) {
        long delay = 0;
        for (Preferences p : preferencesList) {
            addSubscription(p, delay);
            delay += 1000;
        }
    }

    @Override
    public void addSubscription(Preferences preferences) {
        addSubscription(preferences, 0);
    }

    public void addSubscription(Preferences preferences, long initialDelay) {
        Runnable fetcherRunnable = fetcherRunnableFactory.create(preferences);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(fetcherRunnable, initialDelay,
                preferences.getDataProvider().getMillisecondInterval(), TimeUnit.MILLISECONDS);

        activeRunnablesMap.put(preferences.getId(), scheduledFuture);
    }

    @Override
    public void cancelSubscription(Preferences preferences) {
        ScheduledFuture<?> scheduledFuture = activeRunnablesMap.get(preferences.getId());

        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            activeRunnablesMap.remove(preferences.getId());
        }
    }
}

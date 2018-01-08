package fetcher;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dto.NewsDTO;
import interfaces.FetcherRunnableFactory;
import interfaces.IFetchingManager;
import io.reactivex.Observable;
import model.Preferences;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public class FetchingManager implements IFetchingManager {
    @Inject
    private Observable<NewsDTO> newsObservable;
    @Inject
    private FetcherRunnableFactory fetcherRunnableFactory;
    @Inject
    private ScheduledExecutorService scheduledExecutorService;

    private final Map<Long, ScheduledFuture<?>> activeRunnablesMap = new HashMap<>();


    @Override
    public Observable<NewsDTO> getNewsObservable() {
        return newsObservable;
    }

    public void addSubscription(Preferences preferences) {
        Runnable fetcherRunnable = fetcherRunnableFactory.create(preferences);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(fetcherRunnable, 0,
                preferences.getDataProvider().getMillisecondInterval(), TimeUnit.MILLISECONDS);

        activeRunnablesMap.put(preferences.getId(), scheduledFuture);
    }

    public void cancelSubscription(Preferences preferences) {
        ScheduledFuture<?> scheduledFuture = activeRunnablesMap.get(preferences.getId());

        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            activeRunnablesMap.remove(preferences.getId());
        }
    }
}

package util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import interfaces.RetryingExecutor;
import interfaces.RetryingRunnableFactory;

import java.util.concurrent.*;

@Singleton
public class RetryingScheduledExecutor extends ScheduledThreadPoolExecutor implements RetryingExecutor {
    private RetryingRunnableFactory retryingRunnableFactory;


    @Inject
    public RetryingScheduledExecutor(RetryingRunnableFactory retryingRunnableFactory, @CorePoolSize int corePoolSize) {
        super(corePoolSize);
        this.retryingRunnableFactory = retryingRunnableFactory;
        setRemoveOnCancelPolicy(true);
    }


    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        Runnable retryingRunnable = retryingRunnableFactory.create(command, period, unit);
        return super.scheduleAtFixedRate(retryingRunnable, initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        Runnable retryingRunnable = retryingRunnableFactory.create(command, delay, unit);
        return super.scheduleWithFixedDelay(retryingRunnable, initialDelay, delay, unit);
    }

    @Override
    public ScheduledFuture<?> retry(Runnable command, long delay, TimeUnit unit) {
        return schedule(command, delay, unit);
    }
}

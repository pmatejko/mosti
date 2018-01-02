package interfaces;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface RetryingExecutor {
    ScheduledFuture<?> retry(Runnable command, long delay, TimeUnit unit);
}

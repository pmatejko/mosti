package interfaces;

import util.RetryingRunnable;

import java.util.concurrent.TimeUnit;

public interface RetryingRunnableFactory {
    RetryingRunnable create(Runnable command, long entireInterval, TimeUnit unit);
}

package util;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import exceptions.FetchingException;
import interfaces.RetryingExecutor;

import java.util.concurrent.TimeUnit;

public class RetryingRunnable implements Runnable {
    private final RetryingExecutor retryingExecutor;
    private final Runnable command;
    private final long entireInterval;
    private final TimeUnit unit;

    private long nextDelay;
    private long nextTryTime;


    @Inject
    public RetryingRunnable(RetryingExecutor retryingExecutor,
                            @Assisted Runnable command, @Assisted long entireInterval, @Assisted TimeUnit unit) {
        this.retryingExecutor = retryingExecutor;
        this.command = command;
        this.entireInterval = entireInterval;
        this.unit = unit;

        resetTimes();
    }


    @Override
    public void run() {
        long before = System.nanoTime();

        try {
            command.run();
            resetTimes();
        } catch (FetchingException e) {
            long after = System.nanoTime();
            long commandRunTime = unit.convert(after - before, TimeUnit.NANOSECONDS);
            nextTryTime += commandRunTime + nextDelay;

            if (nextTryTime < entireInterval / 2) {
                nextDelay *= 2;
                retryingExecutor.retry(this, nextDelay, unit);
            } else {
                resetTimes();
            }
        }
    }

    private void resetTimes() {
        long firstDelay = entireInterval / 32;

        this.nextDelay = firstDelay == 0 ? 1 : firstDelay;
        this.nextTryTime = 0;
    }
}

package interfaces;

import fetcher.FetcherRunnable;
import model.Preferences;

public interface FetcherRunnableFactory {
    FetcherRunnable create(Preferences preferences);
}

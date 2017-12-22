package interfaces;

import fetcher.FetcherTask;
import model.Preferences;

public interface FetcherTaskFactory {
    FetcherTask create(Preferences preferences);
}

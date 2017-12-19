package interfaces;

import model.News;
import model.Preferences;

import java.util.List;

public interface Fetcher {
    List<News> fetch(Preferences preferences) throws Exception;
}

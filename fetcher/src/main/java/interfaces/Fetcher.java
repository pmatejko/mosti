package interfaces;

import exceptions.FetchingException;
import model.DataProvider;
import model.News;
import model.Preferences;

import java.util.List;

public interface Fetcher {
    List<News> fetch(Preferences preferences) throws FetchingException;
    boolean isCompatible(DataProvider dataProvider);
}

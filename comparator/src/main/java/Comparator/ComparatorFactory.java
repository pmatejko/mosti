package Comparator;


import exceptions.DataProviderCOnflictException;
import interfaces.IComparator;
import model.DataProvider;
import model.News;
import model.Preferences;

import java.util.List;

public class ComparatorFactory {
    IComparator createComparator(News news) throws DataProviderCOnflictException {
        if(news.getPreferences().isEmpty())
            return new ScrapComparator(news);
        else
            return chooseComparator(news);

    }

    private IComparator chooseComparator(News news) throws DataProviderCOnflictException {
        DataProvider provider  = readAndValidate(news.getPreferences());
        if(provider.equals(DataProvider.NEWS_API))
            return new NewsComparator(news);
        else return new TwitterComparator(news);
    }

    private DataProvider readAndValidate(List<Preferences> preferences) throws DataProviderCOnflictException {
        DataProvider providerCandidate = preferences.get(0).getDataProvider();
        for (Preferences preference : preferences)
            if (! preference.getDataProvider().equals(providerCandidate))
                throw new DataProviderCOnflictException(providerCandidate,preference.getDataProvider());

        return providerCandidate;
    }


}

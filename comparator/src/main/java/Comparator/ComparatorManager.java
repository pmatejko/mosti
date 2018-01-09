package Comparator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.NewsDao;
import dto.NewsDTO;
import interfaces.IFetchingManager;


@Singleton
public class ComparatorManager {

    private IFetchingManager fetchingManager;
    private NewsDao newsDao;

    @Inject
    public ComparatorManager(IFetchingManager fetchingManager, NewsDao newsDao) {
        this.fetchingManager = fetchingManager;
        this.newsDao = newsDao;
    }

    public void subscribe() {
        fetchingManager
                .getNewsObservable()
                .subscribe(this::process);
    }

    public void process(NewsDTO news) {
        news.getNewsList()
                .forEach(newsDao::updateOrCreate);
    }
}

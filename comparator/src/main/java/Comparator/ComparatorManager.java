package Comparator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.NewsDao;
import dto.NewsDTO;
import fetcher.FetchingManager;



@Singleton
public class ComparatorManager {
    @Inject
    private FetchingManager fetchingManager;

    @Inject
    private NewsDao newsDao;

    @Inject
    private ComparatorAggregate comparatorAggregate;

    private ComparatorManager(){
        fetchingManager.getNewsObservable().subscribe(this::process);
    }

    public void process(NewsDTO news){
        news.getNewsList().stream()
                .map(newsDao::getOrCreate)
                .forEach(comparatorAggregate::process);
    }
}

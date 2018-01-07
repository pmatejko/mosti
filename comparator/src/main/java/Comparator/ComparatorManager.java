package Comparator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.NewsDao;
import dto.NewsDTO;
import interfaces.IFetchingManager;


@Singleton
public class ComparatorManager {
    @Inject
    private IFetchingManager fetchingManager;

    @Inject
    private NewsDao newsDao;

    public ComparatorManager(){
    }
    public void subscribe(){
        fetchingManager.getNewsObservable().subscribe(this::process);
    }

    public void process(NewsDTO news){
        news.getNewsList()
                .forEach(newsDao::updateOrCreate);
    }
}

package Comparator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dto.NewsDTO;
import exceptions.DataProviderCOnflictException;
import fetcher.FetchingManager;
import model.News;



@Singleton
public class ComparatorManager {
    @Inject
    private FetchingManager fetchingManager;

    @Inject
    private ComparatorFactory comparatorFactory;

    private ComparatorManager(){
        fetchingManager.getNewsObservable().subscribe(this::process);
    }

    public void process(NewsDTO news){
        for (News newsPiece: news.getNewsList()) {
            try {
                comparatorFactory
                        .createComparator(newsPiece)
                        .process();
            } catch (DataProviderCOnflictException e) {
                e.printStackTrace();
            }
        }
    }
}

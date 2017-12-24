package Comparator;

import exceptions.DataProviderCOnflictException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.News;
import dto.NewsDTO;



@Singleton
public class ComparatorManager {

    @Inject
    private ComparatorFactory comparatorFactory;

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

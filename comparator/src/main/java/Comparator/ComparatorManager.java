package Comparator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import entities.NewsDTO;
import interfaces.IComparator;

@Singleton
public class ComparatorManager {

    @Inject
    private ComparatorFactory comparatorFactory;

    public void process(NewsDTO news){
       news.getNewsList().stream()
               .map(comparatorFactory::createComparator)
               .forEach(IComparator::process);
    }
}

package Comparator;


import interfaces.IComparator;
import model.News;

public class ComparatorFactory {
    IComparator createComparator(News news){
        if(news.hasKeywords())
            return new NewsComparator(news);
        else return new ScrapComparator(news);

    }
}

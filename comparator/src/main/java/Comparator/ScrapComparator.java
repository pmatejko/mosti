package Comparator;

import interfaces.IComparator;
import model.News;
import model.User;

public class ScrapComparator extends AbstractComparator{
    ScrapComparator(News news){
        super(news);
    }

    @Override
    public void  process() {

    }

    @Override
    public boolean isNew() {
        return false;
    }



}

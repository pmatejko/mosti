package Comparator;


import model.News;

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

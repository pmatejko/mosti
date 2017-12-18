package Comparator;

import model.News;

public class NewsComparator extends  AbstractComparator{

    public NewsComparator(News news) {
        super(news);
    }

    @Override
    public void process() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}

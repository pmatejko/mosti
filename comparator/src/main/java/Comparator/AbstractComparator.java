package Comparator;

import interfaces.IComparator;
import model.News;
import model.User;

public abstract class AbstractComparator implements IComparator {
    private final News news;

    public AbstractComparator(News news) {
        this.news = news;
    }
    @Override
    public Iterable<User> assignUsersByKeywords() {
        return null;
    }


    @Override
    public void save() {

    }
}

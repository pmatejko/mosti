package Comparator;

import com.google.inject.Inject;
import dao.NewsDao;
import dao.UserDao;
import interfaces.IComparator;
import model.News;
import model.User;

public abstract class AbstractComparator implements IComparator {
    protected final News news;

    @Inject
    NewsDao newsDao;


    public AbstractComparator(News news) {
        this.news = news;
    }

    @Override
    public boolean isUsed() {
        return newsDao.isUsed(news);
    }



}

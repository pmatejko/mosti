package Comparator;

import com.google.inject.Inject;
import dao.NewsDao;
import interfaces.IComparator;
import model.News;

public abstract class AbstractComparator implements IComparator {
    protected final News news;
    NewsDao newsDao;
    @Inject
    public AbstractComparator(News news) {
        this.news = news;
    }
    public void setNewsDao(NewsDao newsDao){
        this.newsDao = newsDao;
    }

    @Override
    public boolean isUsed() {
        return newsDao.isUsed(news);
    }



}

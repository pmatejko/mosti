package Comparator;

import model.News;

public class NewsComparator extends  AbstractComparator{

    public NewsComparator(News news) {
        super(news);
    }

    @Override
    public void process() {
        if (isUsed() && isNew())
            newsDao.create(news);

    }

    @Override
    public boolean isNew() {
        Iterable<News> otherNews = newsDao.findByUrl(news);
        for(News newsPiece : otherNews)
            if (newsPiece.getContent().equals(news.getContent()))
                return false;
        return true;
    }
}

package Comparator;

import interfaces.IComparator;
import model.News;

public class LengthComparator implements IComparator{


    @Override
    public void process(News news) {

    }


//    @Override
//    public boolean isNew() {
////        Iterable<News> otherNews = newsDao.findByUrl(news);
////        for(News newsPiece : otherNews)
////            if (newsPiece.getContent().equals(news.getContent()))
////                return false;
//return true;
//    }
}

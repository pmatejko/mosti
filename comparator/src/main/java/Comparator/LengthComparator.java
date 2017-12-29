package Comparator;

import dao.CompareTypeDao;
import interfaces.IComparator;
import model.CompareType;
import model.News;
import dao.NewsDao;

import javax.inject.Inject;
import java.util.StringTokenizer;




public class LengthComparator implements IComparator{
    @Inject
    private CompareTypeDao compareTypeDao;


    @Override
    public void process(News news) {
        StringTokenizer stringTokenizer= new StringTokenizer(news.getContent());
        int wordsAmount=stringTokenizer.countTokens();
        if(wordsAmount<130){
            CompareType compareType= compareTypeDao.getCompareTypeByName("length");
            compareType.addNews(news);
            compareTypeDao.update(compareType);
        }

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

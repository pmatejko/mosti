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
    @Inject private NewsDao newsDao;


    @Override
    public boolean process(News news) { // wywalić zapis do bazy. Wgl kontakt z bazą. Ma sprawdzać, czy warunek spełniony i zwracać bool
        StringTokenizer stringTokenizer= new StringTokenizer(news.getContent());
        int wordsAmount=stringTokenizer.countTokens();
        if(wordsAmount<130){ // dodać ustawianie ile słów
            CompareType compareType= compareTypeDao.getCompareTypeByName("length");
            news.addCompareType(compareType);
            newsDao.update(news);

        }

    }

}

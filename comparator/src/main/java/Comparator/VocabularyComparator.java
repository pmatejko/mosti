package Comparator;


import dao.CompareTypeDao;
import dao.NewsDao;
import interfaces.IComparator;
import model.CompareType;
import model.News;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class VocabularyComparator implements IComparator{

    @Inject
    private CompareTypeDao compareTypeDao;

    @Inject
    private NewsDao newsDao;


    @Override
    public boolean process(News news) {   // wywalić zapis do bazy. Wgl kontakt z bazą. Ma sprawdzać, czy warunek spełniony i zwracać bool
       StringTokenizer stringTokenizer= new StringTokenizer(news.getContent());
        Set<String> uniqueWords = new HashSet<>();
        while (stringTokenizer.hasMoreElements()){
            uniqueWords.add(stringTokenizer.nextToken());
        }
        if(uniqueWords.size()<300){ // dodać ustawianie ile słów
            CompareType compareType= compareTypeDao.getCompareTypeByName("vocabulary");
            news.addCompareType(compareType);
            newsDao.update(news);
        }
    }


}

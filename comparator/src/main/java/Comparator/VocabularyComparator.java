package Comparator;


import dao.CompareTypeDao;
import interfaces.IComparator;
import model.CompareType;
import model.News;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class VocabularyComparator implements IComparator{

    @Inject
    private CompareTypeDao compareTypeDao;

    @Override
    public void process(News news) {
       StringTokenizer stringTokenizer= new StringTokenizer(news.getContent());
        Set<String> uniqueWords = new HashSet<>();
        while (stringTokenizer.hasMoreElements()){
            uniqueWords.add(stringTokenizer.nextToken());
        }
        if(uniqueWords.size()<300){
            CompareType compareType= compareTypeDao.getCompareTypeByName("vocabulary");
            compareType.addNews(news);
            compareTypeDao.update(compareType);
        }
    }


}

package Comparator;


import dao.CompareTypeDao;
import dao.NewsDao;
import interfaces.IComparator;
import model.Condition;
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
    public void process(News news) {

    }


}

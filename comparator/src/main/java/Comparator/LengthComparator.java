package Comparator;

import dao.CompareTypeDao;
import interfaces.IComparator;
import model.Condition;
import model.News;
import dao.NewsDao;

import javax.inject.Inject;
import java.util.StringTokenizer;




public class LengthComparator implements IComparator{
    @Inject
    private CompareTypeDao compareTypeDao;
    @Inject private NewsDao newsDao;


    @Override
    public void process(News news) {


    }

}

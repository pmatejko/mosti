import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.ComparatorModule;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.NewsDaoImpl;
import dto.NewsDTO;
import model.CompareType;
import model.DataProvider;
import model.News;
import model.Preferences;

import java.util.Arrays;
import java.util.Date;

public class Main {


    public static void main(String[] args){
        CompareTypeDaoImpl c= new CompareTypeDaoImpl();
        CompareType c1 = new CompareType();
        c1.setType("length");
        CompareType c2 = new CompareType();
        c2.setType("vocabulary");
        c.save(c1);
        c.save(c2);

        Injector injector = Guice.createInjector(new Config());
        ComparatorManager comparatorManager = injector.getInstance(ComparatorManager.class);
        News n1 = new News(new Preferences("k","g", DataProvider.NEWS_API),"asdasdas,uel","asdas",new Date());
        News n2 = new News(new Preferences("s","g", DataProvider.NEWS_API),"asdasdasdsadas,uel","asasdedwdas",new Date());
        NewsDTO x = new NewsDTO(Arrays.asList(n1,n2));
        comparatorManager.process(x);
        System.out.println("dupa");
    }
}

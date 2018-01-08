import Comparator.ComparatorManager;
import Daemon.NotifierDeamon;
import com.google.inject.Guice;
import com.google.inject.Injector;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.NewsDaoImpl;
import daoImpl.UserDaoImpl;
import dto.NewsDTO;
import model.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args){
        CompareTypeDaoImpl c= new CompareTypeDaoImpl();
        CompareType compareType = new CompareType();
        compareType.setType("length");
        CompareType compareType1 = new CompareType();
        compareType1.setType("vocabulary");
        c.save(compareType);
        c.save(compareType1);

        Injector injector = Guice.createInjector(new Config());
        NotifierDeamon notifierDeamon = injector.getInstance(NotifierDeamon.class);
//        News n1 = new News(new Preferences("k","g", DataProvider.NEWS_API),"asdasdas,uel","asdas",new Date());
//        News n2 = new News(new Preferences("s","g", DataProvider.NEWS_API),"asdasdasdsadas,uel","asasdedwdas",new Date());
//        NewsDTO x = new NewsDTO(Arrays.asList(n1,n2));
//        comparatorManager.process(x);
//        System.out.println("dupa");


        News news= new News();
        News news1= new News();
        User user= new User();
        UserDaoImpl userDao= new UserDaoImpl();

        Preferences preferences=new Preferences();

        List<Preferences> p= new LinkedList<>();
        Preferences preferences1=new Preferences();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timestamp tt = new Timestamp(System.currentTimeMillis());

        preferences.setDataProvider(DataProvider.NEWS_API);
        preferences1.setDataProvider(DataProvider.NEWS_API);

        preferences.addNews(news);
        preferences1.addNews(news);
        preferences1.addNews(news1);
        preferences.addNews(news1);
        preferences.addUser(user);
        preferences1.addUser(user);


        p.add(preferences);
        p.add(preferences1);

        user.setEmail("nana");
        user.setInterval(1);
        userDao.save(user);
        user.setLastNotification(t);
        user.setPreferences(p);
        user.addCompareType(compareType);
        user.addCompareType(compareType1);


        news.setUrl("mamama");
        news.setContent("mamamam");
        news1.setUrl("superurl");
        news1.setContent("llllllllllllllll");
        news.setTimestamp(t);
        news1.setTimestamp(tt);
        news.addCompareType(compareType);
        news1.addCompareType(compareType);
        news.addPreference(preferences);
        news.addPreference(preferences1);
        news1.addPreference(preferences);
        news1.addPreference(preferences1);

        compareType.addUser(user);
        compareType1.addUser(user);
        compareType.addNews(news);
        compareType.addNews(news1);
        compareType1.addNews(news);
        compareType1.addNews(news1);

        //  NewsDaoImpl newsDao=new NewsDaoImpl();
        //  newsDao.save(news1);

        //  PreferencesDaoImpl preferencesDao= new PreferencesDaoImpl();
        // CompareTypeDaoImpl compareTypeDao= new CompareTypeDaoImpl();
        //  newsDao.save(news);

        // compareTypeDao.save(compareType);
//        compareTypeDao.save(compareType1);
        userDao.update(user);
//        PreferencesDaoImpl pre=







        notifierDeamon.getUserNewsObservable().subscribe(System.out::println);
        notifierDeamon.run();
        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

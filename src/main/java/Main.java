import Comparator.ComparatorFactory;
import Comparator.ComparatorManager;
import Comparator.LengthComparator;
import Daemon.NotifierDeamon;
import com.google.inject.Guice;
import com.google.inject.Injector;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.UserDaoImpl;
import interfaces.IComparator;
import model.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args){

//        CompareTypeDaoImpl c= new CompareTypeDaoImpl();
//        Condition condition = new Condition();
//        condition.setType(ConditionType.LENGTH);
//        condition.setValue(100);
//        Condition condition1 = new Condition();
//        condition1.setType(ConditionType.VOCABULARY);
//        condition1.setValue(150);
//        c.save(condition);
//        c.save(condition1);

        Injector injector = Guice.createInjector(new Config());
        NotifierDeamon notifierDeamon = injector.getInstance(NotifierDeamon.class);
//        Set<IComparator> x = Config.getSomeClass(injector);
//        x.forEach(i -> System.out.println(i.getClass().toString()));
//        News n1 = new News(new Preferences("k","g", DataProvider.NEWS_API),"asdasdas,uel","asdas",new Date());
//        News n2 = new News(new Preferences("s","g", DataProvider.NEWS_API),"asdasdasdsadas,uel","asasdedwdas",new Date());
//        NewsDTO x = new NewsDTO(Arrays.asList(n1,n2));
//        comparatorManager.process(x);
//        System.out.println("dupa");
        ComparatorManager cm = injector.getInstance(ComparatorManager.class);




//
//        News news= new News();
//        News news1= new News();
        User user= new User();
        UserDaoImpl userDao= new UserDaoImpl();

        Preferences preferences=new Preferences();
        Preferences preferences5 = new Preferences("maga", "realDonaldTrump", DataProvider.TWITTER_API);

        List<Preferences> p= new LinkedList<>();
        Preferences preferences1=new Preferences();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timestamp tt = new Timestamp(System.currentTimeMillis());

        preferences.setDataProvider(DataProvider.NEWS_API);
        preferences.setKeyword("Trump");
        preferences1.setDataProvider(DataProvider.TWITTER_API);
        preferences1.setKeyword("bitcoin");


        Preferences preferences2=new Preferences();


        Preferences preferences3=new Preferences();
        preferences2.setDataProvider(DataProvider.NEWS_API);
        preferences3.setKeyword("Trump");
        preferences3.setDataProvider(DataProvider.TWITTER_API);
        preferences2.setKeyword("bitcoin");


        preferences.addUser(user);
       // preferences.setNewsSource("bbc");
        preferences1.addUser(user);
        preferences2.addUser(user);
       preferences2.setNewsSource("bbc");
        preferences3.addUser(user);
        preferences5.addUser(user);


        p.add(preferences);
        p.add(preferences1);
        p.add(preferences2);
        p.add(preferences3);
        p.add(preferences5);

        user.setEmail("nana");
        user.setInterval(1);
        userDao.save(user);
        user.setLastNotification(t);
        user.setPreferences(p);
//        user.addCompareType(condition);
//        user.addCompareType(condition1);


//        news.setUrl("mamama");
//        news.setContent("mamamam asd");
//        news1.setUrl("superurl");
//        news1.setContent("llllllllllllllll aawd  ");
//        news.setTimestamp(t);
//        news1.setTimestamp(tt);
//        news.addPreference(preferences);
//        news.addPreference(preferences1);
//        news1.addPreference(preferences);
//        news1.addPreference(preferences1);

//        condition.addUser(user);
//        condition1.addUser(user);

//
//        //  NewsDaoImpl newsDao=new NewsDaoImpl();
//        //  newsDao.save(news1);
//
//        //  PreferencesDaoImpl preferencesDao= new PreferencesDaoImpl();
//        // CompareTypeDaoImpl compareTypeDao= new CompareTypeDaoImpl();
//        //  newsDao.save(news);
//
//        // compareTypeDao.save(condition);
////        compareTypeDao.save(condition1);
        userDao.update(user);
////        PreferencesDaoImpl pre=
//
//
//
//
//
//
//
//        for (Preferences pr : p)
//            cm.fetchingManager.addSubscription(pr);
        cm.subscribe();
        notifierDeamon.getUserNewsObservable().subscribe(System.out::println);
        notifierDeamon.run();
        try {
            Thread.sleep(650000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import daoImpl.UserDaoImpl;
import interfaces.IProvider;
import interfaces.SubscriptionManager;
import model.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args){

        Injector injector = Guice.createInjector(new Config());
        IProvider iProvider = injector.getInstance(IProvider.class);
        ComparatorManager cm = injector.getInstance(ComparatorManager.class);
        SubscriptionManager sm = injector.getInstance(SubscriptionManager.class);


/////////////////////////////////////////////////////////////////////
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




        userDao.update(user);
///////////////////////////////////////////////////////////

        for (Preferences pr : p)
            sm.addSubscription(pr);
        cm.subscribe();
        iProvider.getUserNewsObservable().subscribe(System.out::println);
        try {
            Thread.sleep(650000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

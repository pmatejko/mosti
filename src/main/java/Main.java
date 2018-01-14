import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import daoImpl.UserDaoImpl;
import interfaces.SubscriptionManager;
import model.*;
import notifier.informer.NotifierManager;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args){

        Injector injector = Guice.createInjector(new Config());
        ComparatorManager cm = injector.getInstance(ComparatorManager.class);
        SubscriptionManager sm = injector.getInstance(SubscriptionManager.class);
        NotifierManager notifierManager = injector.getInstance(NotifierManager.class);

/////////////////////////////////////////////////////////////////////
        User user= new User();
        UserDaoImpl userDao= new UserDaoImpl();

        Preferences preferences=new Preferences();


        List<Preferences> p= new LinkedList<>();
        Preferences preferences1=new Preferences();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timestamp tt = new Timestamp(System.currentTimeMillis());

        preferences1.setDataProvider(DataProvider.NEWS_API);
        preferences1.setKeyword("politics");
        preferences.setDataProvider(DataProvider.TWITTER_API);
        preferences.setKeyword("politics");


        Preferences preferences2=new Preferences();
//
//
        Preferences preferences3=new Preferences();
        preferences2.setDataProvider(DataProvider.NEWS_API);
        preferences3.setKeyword("bitcoin");
        preferences3.setDataProvider(DataProvider.TWITTER_API);
        preferences2.setKeyword("trump");
//

        preferences.addUser(user);
       // preferences.setNewsSource("bbc");
        preferences1.addUser(user);
        preferences2.addUser(user);
//       preferences2.setNewsSource("bbc");
        preferences3.addUser(user);



        p.add(preferences);
        p.add(preferences1);
        p.add(preferences2);
        p.add(preferences3);


        user.setEmail("kaaaaaa@gmail.com");
        user.setInterval(1);
        userDao.save(user);
        user.setLastNotification(t);
        user.setPreferences(p);




        userDao.update(user);
///////////////////////////////////////////////////////////

        for (Preferences pr : p)
            sm.addSubscription(pr);
        cm.subscribe();
        notifierManager.subscribe();
        try {
            Thread.sleep(650000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

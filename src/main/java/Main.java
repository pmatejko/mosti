import Comparator.ComparatorFactory;
import Comparator.ComparatorManager;
import Comparator.LengthComparator;
import Daemon.NotifierDeamon;
import com.google.inject.Guice;
import com.google.inject.Injector;
import daoImpl.ConditionDaoImpl;
import daoImpl.UserDaoImpl;
import interfaces.IComparator;
import model.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {




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
        ComparatorFactory comp = injector.getInstance(ComparatorFactory.class);
        System.out.println("dupa");
        IComparator comp1 = new LengthComparator();


        createDatabase();


    }

    private static void createDatabase() {

        ConditionDaoImpl c = new ConditionDaoImpl();
        Condition condition = new Condition();
        condition.setType(ConditionType.LENGTH);
        Condition condition1 = new Condition();
        condition1.setType(ConditionType.VOCABULARY);
        condition.setValue(5);
        condition1.setValue(10);

        News news = new News();
        News news1 = new News();
        User user = new User();


        Preferences preferences = new Preferences();

        List<Preferences> p = new LinkedList<>();
        Preferences preferences1 = new Preferences();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timestamp tt = new Timestamp(System.currentTimeMillis());

        preferences.setDataProvider(DataProvider.NEWS_API);
        preferences1.setDataProvider(DataProvider.TWITTER_API);
        preferences.addNews(news);
        preferences1.addNews(news);
        preferences1.addNews(news1);
        preferences.addNews(news1);
        preferences.addUser(user);
        preferences1.addUser(user);
        preferences.setNewsSource("source");
        preferences1.setNewsSource("source1");
        preferences.setKeyword("key");
        preferences1.setKeyword("word");

        p.add(preferences);
        p.add(preferences1);

        user.setEmail("nana");
        user.setInterval(1);
        user.setLastNotification(t);
        user.setPreferences(p);
        user.addCompareType(condition);
        user.addCondition(condition);
        user.addCondition(condition1);
        user.addCompareType(condition1);


        news.setUrl("mamama");
        news.setContent("mamamam na na naa an anna nna na na");
        news1.setUrl("superurl");
        news1.setContent("l 2 3 4 5 6 7 8 9 0 ");
        news.setTimestamp(t);
        news1.setTimestamp(tt);
        news.addPreference(preferences);
        news.addPreference(preferences1);
        news1.addPreference(preferences);
        news1.addPreference(preferences1);
        news.setTimestamp(t);
        news1.setTimestamp(tt);


        condition.addUser(user);
        condition1.addUser(user);


        UserDaoImpl userDao = new UserDaoImpl();
        userDao.save(user);
    }
}

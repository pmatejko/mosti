import com.google.inject.Guice;
import com.google.inject.Injector;
import fetcher.FetchingManager;
import guice.FetcherModule;
import model.DataProvider;
import model.Preferences;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Preferences> preferences = new LinkedList<>();
        preferences.add(new Preferences("maga", "realDonaldTrump", DataProvider.TWITTER_API));
//        preferences.add(new Preferences("bitcoin", "bbc", DataProvider.NEWS_API));
//        preferences.add(new Preferences("star wars", "vice-news", DataProvider.NEWS_API));
//        preferences.add(new Preferences(null, "thooorin", DataProvider.TWITTER_API));

        Injector injector = Guice.createInjector(new FetcherModule());
        FetchingManager fm = injector.getInstance(FetchingManager.class);

        fm.getNewsObservable().subscribe(newsDTO ->
                newsDTO.getNewsList()
                        .forEach(news -> System.out.println(news + "\n")));

        for (Preferences p : preferences)
            fm.addSubscription(p);

        Thread.sleep(15 * 1000);
        for (Preferences p : preferences)
            fm.cancelSubscription(p);
    }
}

package fetcher;

import dao.PreferencesDao;
import dto.NewsDTO;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import interfaces.FetcherProvider;
import interfaces.FetcherRunnableFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import model.DataProvider;
import model.News;
import model.Preferences;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FetchingManagerTest {
    private Observer<NewsDTO> newsObserver;
    private Observable<NewsDTO> newsObservable;


    @Mock
    private FetcherRunnableFactory fetcherRunnableFactory;
    @Mock
    private PreferencesDao preferencesDao;
    @Mock
    private FetcherProvider fetcherProvider;
    @Mock
    private ScheduledExecutorService scheduledExecutorService;
    @Mock
    private TwitterAPIFetcher twitterAPIFetcher;
    @Mock
    private NewsAPIFetcher newsAPIFetcher;

    @InjectMocks
    private FetchingManager fetchingManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this.getClass());
        PublishSubject<NewsDTO> newsDTOPublishSubject = PublishSubject.create();
        newsObservable = newsDTOPublishSubject;
        newsObserver = newsDTOPublishSubject;

        when(preferencesDao.getAllPreferences())
                .thenAnswer((Answer<List<Preferences>>) invocation -> {
            List<Preferences> preferencesList = new LinkedList<>();
            preferencesList.add(new Preferences("bitcoin", "bbc.co.uk", DataProvider.NEWS_API));
            preferencesList.add(new Preferences("maga", "realDonaldTrump", DataProvider.TWITTER_API));
            return preferencesList;
        });

//        when(twitterAPIFetcher.fetch(any(Preferences.class))).thenAnswer(invocation -> {
//            Preferences p = invocation.getArgument(0);
//            News n1 = new News(p, "url1.com", "content1", Timestamp.from(Instant.now()));
//            News n2 = new News(p, "url2.com", "content2", Timestamp.from(Instant.now()));
//            List<News> newsList = new LinkedList<>();
//            newsList.add(n1);
//            newsList.add(n2);
//
//            return newsList;
//        });
//
//        when(newsAPIFetcher.fetch(any(Preferences.class))).thenAnswer(invocation -> {
//            Preferences p = invocation.getArgument(0);
//            News n1 = new News(p, "url3.com", "content3", Timestamp.from(Instant.now()));
//            News n2 = new News(p, "url4.com", "content4", Timestamp.from(Instant.now()));
//            List<News> newsList = new LinkedList<>();
//            newsList.add(n1);
//            newsList.add(n2);
//
//            return newsList;
//        });
//
//        when(fetcherProvider.getFetcher(DataProvider.TWITTER_API)).thenAnswer(invocation -> twitterAPIFetcher);
//        when(fetcherProvider.getFetcher(DataProvider.NEWS_API)).thenAnswer(invocation -> newsAPIFetcher);

        when(fetcherRunnableFactory.create(any(Preferences.class))).thenAnswer(invocation -> {
           Preferences p = invocation.getArgument(0);
           return new FetcherRunnable(newsObserver, fetcherProvider, p);
        });

        when(scheduledExecutorService.scheduleAtFixedRate(any(Runnable.class), anyLong(), anyLong(), any(TimeUnit.class)))
                .thenAnswer(invocation -> {
                    ScheduledFuture scheduledFuture = mock(ScheduledFuture.class);
                    return scheduledFuture;
                }
        );

        fetchingManager = new FetchingManager(newsObservable, fetcherRunnableFactory, scheduledExecutorService, preferencesDao);
    }

    @Test
    public void getNewsObservableTest() {
        AtomicInteger number = new AtomicInteger(1);

        fetchingManager.getNewsObservable().subscribe(newsDTO -> {
            List<News> newsList = newsDTO.getNewsList();
            for (News news : newsList) {
                assertEquals(news.getContent(), "content" + number);
                assertEquals(news.getUrl(), "url" + number + ".com");
                number.addAndGet(1);
            }
        });

        Preferences p1 = mock(Preferences.class);
        News n1 = new News(p1, "url1.com", "content1", Timestamp.from(Instant.now()));
        News n2 = new News(p1, "url2.com", "content2", Timestamp.from(Instant.now()));
        List<News> newsList1 = new LinkedList<>();
        newsList1.add(n1);
        newsList1.add(n2);

        Preferences p2 = mock(Preferences.class);
        News n3 = new News(p2, "url3.com", "content3", Timestamp.from(Instant.now()));
        News n4 = new News(p2, "url4.com", "content4", Timestamp.from(Instant.now()));
        List<News> newsList2 = new LinkedList<>();
        newsList2.add(n3);
        newsList2.add(n4);

        newsObserver.onNext(new NewsDTO(newsList1));
        newsObserver.onNext(new NewsDTO(newsList2));
    }

}
import Comparator.NewsComparator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.ComparatorModule;
import dao.NewsDao;
import model.News;
import org.junit.Test;
import org.junit.Assert.*;


import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NewsComparatorTest {

    @Test
    public void isnotNewTest(){
        News news = new News();
        news.setContent("lala");
        String url = "www.lala.com";
        news.setUrl(url);
        NewsComparator newsComparator = new NewsComparator(news);
        NewsDao newsDao = mock(NewsDao.class);
        when(newsDao.findByUrl(news))
                .thenReturn(Arrays.asList(new News(url,"baba"),new News(url,"abba")));
        newsComparator.setNewsDao(newsDao);
        assertTrue(newsComparator.isNew());
    }

    @Test
    public void isNewTest(){
        News news = new News();
        news.setContent("lala");
        String url = "www.lala.com";
        news.setUrl(url);
        NewsComparator newsComparator = new NewsComparator(news);
        NewsDao newsDao = mock(NewsDao.class);
        when(newsDao.findByUrl(news))
                .thenReturn(Arrays.asList(new News(url,"lala"),new News(url,"abba")));
        newsComparator.setNewsDao(newsDao);
        assertFalse(newsComparator.isNew());
    }
}

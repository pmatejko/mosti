import Comparator.LengthComparator;
import dao.NewsDao;
import model.News;
import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LengthComparatorTest {

//    @Test
//    public void isnotNewTest(){
//        News news = new News();
//        news.setContent("lala");
//        String url = "www.lala.com";
//        news.setUrl(url);
//        LengthComparator lengthComparator = new LengthComparator(news);
//        NewsDao newsDao = mock(NewsDao.class);
//        when(newsDao.findByUrl(news))
//                .thenReturn(Arrays.asList(new News(url,"baba"),new News(url,"abba")));
//        lengthComparator.setNewsDao(newsDao);
//        assertTrue(lengthComparator.isNew());
//    }
//
//    @Test
//    public void isNewTest(){
//        News news = new News();
//        news.setContent("lala");
//        String url = "www.lala.com";
//        news.setUrl(url);
//        LengthComparator lengthComparator = new LengthComparator(news);
//        NewsDao newsDao = mock(NewsDao.class);
//        when(newsDao.findByUrl(news))
//                .thenReturn(Arrays.asList(new News(url,"lala"),new News(url,"abba")));
//        lengthComparator.setNewsDao(newsDao);
//        assertFalse(lengthComparator.isNew());
//    }
}

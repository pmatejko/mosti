import Comparator.LengthComparator;
import dao.NewsDao;
import model.News;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class LengthComparatorTest {





@Test
public void RigthAmountOfWordsTest() {
    News news = mock(News.class);
    LengthComparator lengthComparator= new LengthComparator(300);

    when(news.getContent()).thenReturn("Lorem ipsum dolor sit amet," +
            " consectetur adipiscing elit. Nunc elementum placerat " +
            "odio faucibus lobortis. Aliquam nec blandit nibh. Vivamus " +
            "malesuada hendrerit risus, vitae sodales orci vestibulum" +
            " non. Vestibulum ante ipsum.");

    assertTrue(lengthComparator.process(news));

}

    @Test
    public void TooManyWordsTest() {
        News news = mock(News.class);
        LengthComparator lengthComparator= new LengthComparator(10);

        when(news.getContent()).thenReturn("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Aliquam blandit convallis tempor. Fusce eget auctor.");

        assertFalse(lengthComparator.process(news));

    }

    @Test
    public void emptyContentTest() {
        News news = mock(News.class);
        LengthComparator lengthComparator= new LengthComparator(10);

        when(news.getContent()).thenReturn(" ");

        assertFalse(lengthComparator.process(news));

    }
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

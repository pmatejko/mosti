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
        LengthComparator lengthComparator = new LengthComparator(300);

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
        LengthComparator lengthComparator = new LengthComparator(10);

        when(news.getContent()).thenReturn("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Aliquam blandit convallis tempor. Fusce eget auctor.");

        assertFalse(lengthComparator.process(news));

    }

    @Test
    public void emptyContentTest() {
        News news = mock(News.class);
        LengthComparator lengthComparator = new LengthComparator(10);

        when(news.getContent()).thenReturn(" ");

        assertFalse(lengthComparator.process(news));

    }

}

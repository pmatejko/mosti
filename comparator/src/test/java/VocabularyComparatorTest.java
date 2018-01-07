import Comparator.LengthComparator;
import Comparator.VocabularyComparator;
import model.News;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VocabularyComparatorTest {

    @Test
    public void RightAmountOfDifferentWordsTest() {
        News news = mock(News.class);
        VocabularyComparator vocabularyComparator= new VocabularyComparator(100);

        when(news.getContent()).thenReturn("Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas consectetur eu nunc " +
                "in pellentesque. Mauris.");

        assertTrue(vocabularyComparator.process(news));

    }

    @Test
    public void TooManyDifferentWordsWordsTest() {
        News news = mock(News.class);
        VocabularyComparator vocabularyComparator= new VocabularyComparator(10);

        when(news.getContent()).thenReturn("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Maecenas eu molestie quam. " +
                "Proin posuere, libero.");

        assertFalse(vocabularyComparator.process(news));

    }


    @Test
    public void EmptyContentTest() {
        News news = mock(News.class);
        VocabularyComparator vocabularyComparator= new VocabularyComparator(10);

        when(news.getContent()).thenReturn(" ");

        assertFalse(vocabularyComparator.process(news));

    }

}

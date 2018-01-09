import Comparator.ComparatorComposite;
import Comparator.LengthComparator;
import Comparator.VocabularyComparator;
import interfaces.IComparator;
import model.News;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComparatorCompositeTest {
    @Test
    public void noConditionSpecyfiedTest() {
        List<IComparator> activeComparators= new LinkedList<>();
        News news = mock(News.class);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);

        assertTrue(comparatorComposite.process(news));

    }


    @Test
    public void satisfiedConditionTest() {
        List<IComparator> activeComparators= new LinkedList<>();
        News news = mock(News.class);
        IComparator comparator= mock(IComparator.class);
        IComparator comparator1= mock(IComparator.class);
        activeComparators.add(comparator);
        activeComparators.add(comparator1);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);


        when(comparator.process(news)).thenReturn(true);
        when(comparator1.process(news)).thenReturn(false);

        assertTrue(comparatorComposite.process(news));

    }

    @Test
    public void noSatisfiedConditionTest() {
        List<IComparator> activeComparators= new LinkedList<>();
        IComparator comparator= mock(IComparator.class);
        IComparator comparator1= mock(IComparator.class);
        activeComparators.add(comparator);
        activeComparators.add(comparator1);
        News news = mock(News.class);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);


        when(comparator.process(news)).thenReturn(false);
        when(comparator1.process(news)).thenReturn(false);

        assertFalse(comparatorComposite.process(news));

    }
}

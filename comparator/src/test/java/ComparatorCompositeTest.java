import Comparator.ComparatorComposite;
import interfaces.IConfigurableComparator;
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
        List<IConfigurableComparator> activeComparators= new LinkedList<>();
        News news = mock(News.class);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);

        assertTrue(comparatorComposite.process(news));

    }


    @Test
    public void satisfiedConditionTest() {
        List<IConfigurableComparator> activeComparators= new LinkedList<>();
        News news = mock(News.class);
        IConfigurableComparator comparator= mock(IConfigurableComparator.class);
        IConfigurableComparator comparator1= mock(IConfigurableComparator.class);
        activeComparators.add(comparator);
        activeComparators.add(comparator1);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);


        when(comparator.process(news)).thenReturn(true);
        when(comparator1.process(news)).thenReturn(false);

        assertTrue(comparatorComposite.process(news));

    }

    @Test
    public void noSatisfiedConditionTest() {
        List<IConfigurableComparator> activeComparators= new LinkedList<>();
        IConfigurableComparator comparator= mock(IConfigurableComparator.class);
        IConfigurableComparator comparator1= mock(IConfigurableComparator.class);
        activeComparators.add(comparator);
        activeComparators.add(comparator1);
        News news = mock(News.class);
        ComparatorComposite comparatorComposite= new ComparatorComposite(activeComparators);


        when(comparator.process(news)).thenReturn(false);
        when(comparator1.process(news)).thenReturn(false);

        assertFalse(comparatorComposite.process(news));

    }
}

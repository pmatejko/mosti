import Comparator.ComparatorComposite;
import Comparator.ComparatorFactory;
import Comparator.LengthComparator;
import interfaces.IComparator;
import model.Condition;
import model.ConditionType;
import model.News;
import model.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComparatorFactoryTest {
    @Test
    public void createComparatorForUserEmptyTest() {
        User user = mock(User.class);
        List<Condition> conditions = new LinkedList<>();
        ComparatorFactory comparatorFactory = new ComparatorFactory();
        when(user.getConditions()).thenReturn(conditions);


        assertEquals(new ComparatorComposite(new LinkedList<>()), comparatorFactory.createComparatorForUser(user));


    }

    @Test
    public void createComparatorForUserNonEmptyTest() {
        User user = mock(User.class);
        List<Condition> conditions = new LinkedList<>();
        Condition condition= mock(Condition.class);
        conditions.add(condition);
        ComparatorFactory comparatorFactory = new ComparatorFactory();

        when(condition.getType()).thenReturn(ConditionType.LENGTH);
        when(condition.getValue()).thenReturn(150);
        when(user.getConditions()).thenReturn(conditions);

        List<IComparator> comparators = new LinkedList<>();
        comparators.add(new LengthComparator(150));
        ComparatorComposite composite = new ComparatorComposite(comparators);



        assertEquals(composite, comparatorFactory.createComparatorForUser(user));


    }
}

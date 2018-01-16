import Comparator.ComparatorComposite;
import Comparator.ComparatorFactory;
import Comparator.LengthComparator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.ComparatorModule;
import interfaces.IConfigurableComparator;
import model.Condition;
import model.ConditionType;
import model.User;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComparatorFactoryTest {
    Injector injector =  Guice.createInjector(new ComparatorModule());
    ComparatorFactory comparatorFactory = injector.getInstance(ComparatorFactory.class);
    @Test
    public void createComparatorForUserEmptyTest() {

        User user = mock(User.class);
        List<Condition> conditions = new LinkedList<>();

        when(user.getConditions()).thenReturn(conditions);


        assertEquals(new ComparatorComposite(new LinkedList<>()), comparatorFactory.createComparatorForUser(user));


    }

    @Test
    public void createComparatorForUserNonEmptyTest() {
        User user = mock(User.class);
        List<Condition> conditions = new LinkedList<>();
        Condition condition= mock(Condition.class);
        conditions.add(condition);

        when(condition.getType()).thenReturn(ConditionType.LENGTH);
        when(condition.getValue()).thenReturn(150);
        when(user.getConditions()).thenReturn(conditions);

        List<IConfigurableComparator> comparators = new LinkedList<>();
        comparators.add(new LengthComparator(150));
        ComparatorComposite composite = new ComparatorComposite(comparators);



        assertEquals(composite, comparatorFactory.createComparatorForUser(user));


    }
}

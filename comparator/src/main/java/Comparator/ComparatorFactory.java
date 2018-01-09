package Comparator;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import interfaces.IComparator;
import interfaces.IConfigurableComparator;
import model.Condition;
import model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ComparatorFactory {
    private final Set<IConfigurableComparator> comparators;

    @Inject
    public ComparatorFactory(Set<IConfigurableComparator> comparators) {
        this.comparators = comparators;
    }
    public IComparator createComparatorForUser(User user){
        List<IConfigurableComparator> comparators =
                user.getConditions().stream()
                .map(this::createComparatorByCondition)
                .collect(Collectors.toList());

        return new ComparatorComposite(comparators);
    }

    private IConfigurableComparator createComparatorByCondition(Condition condition){
        for(IConfigurableComparator comparator : comparators)
            if(comparator.supports(condition))
                return comparator.createFor(condition);
        throw new IllegalArgumentException(condition.getType().toString());
    }

}

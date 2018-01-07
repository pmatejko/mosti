package Comparator;


import interfaces.IComparator;
import model.Condition;
import model.User;

import java.util.LinkedList;
import java.util.List;

public class ComparatorFactory {

    public IComparator createComparatorForUser(User user){
        List<IComparator> comparators = new LinkedList<>();
        user.getConditions().forEach(condition ->
                comparators.add(createComparatorByCondition(condition))
        );
        return new ComparatorComposite(comparators);
    }

    private IComparator createComparatorByCondition(Condition condition) {
    switch(condition.getType()){
        case LENGTH:
            return new LengthComparator(condition.getValue());
        case VOCABULARY:
            return new VocabularyComparator(condition.getValue());
    }
        return null;
    }
}

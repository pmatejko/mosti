package Comparator;


import interfaces.IComparator;
import interfaces.IConfigurableComparator;
import model.Condition;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorComposite implements IComparator {
    private List<IConfigurableComparator> activeComparators = new LinkedList<>();

    ComparatorComposite(List<IConfigurableComparator> activeComparators) {
        this.activeComparators = activeComparators;
    }

    public boolean process(News news) {
        return activeComparators.stream().anyMatch(
                comparator -> comparator.process(news)) || noConditionsSpecified();
    }

    @Override
    public boolean supports(Condition condition) {
        return false;
    }


    private boolean noConditionsSpecified() {
        return activeComparators.isEmpty();
    }

}

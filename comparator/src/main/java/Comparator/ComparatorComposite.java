package Comparator;


import interfaces.IComparator;
import interfaces.IConfigurableComparator;
import model.Condition;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorComposite implements IComparator {
    private List<IConfigurableComparator> activeComparators = new LinkedList<>();

    public ComparatorComposite(List<IConfigurableComparator> activeComparators) {
        this.activeComparators = activeComparators;
    }

    public boolean process(News news) {
        return activeComparators.stream().
                anyMatch(comparator -> comparator.process(news)) || noConditionsSpecified();

    }

    @Override
    public boolean supports(Condition condition) {
        return false;
    }


    private boolean noConditionsSpecified() {
        return activeComparators.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComparatorComposite)) return false;

        ComparatorComposite that = (ComparatorComposite) o;

        return activeComparators.equals(that.activeComparators);
    }

    @Override
    public int hashCode() {
        return activeComparators.hashCode();
    }
}

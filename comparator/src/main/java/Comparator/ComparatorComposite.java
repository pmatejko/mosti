package Comparator;


import interfaces.IComparator;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorComposite implements IComparator {
    private List<IComparator> activeComparators = new LinkedList<>();

    ComparatorComposite(List<IComparator> activeComparators) {
        this.activeComparators = activeComparators;
    }

    public boolean process(News news) {
        return activeComparators.stream().anyMatch(
                comparator -> comparator.process(news) || noConditionsSpecified()
        );
    }

    private boolean noConditionsSpecified() {
        return activeComparators.isEmpty();
    }

}

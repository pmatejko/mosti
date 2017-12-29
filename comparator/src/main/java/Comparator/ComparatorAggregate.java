package Comparator;


import interfaces.IComparator;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorAggregate {
    private List<IComparator> activeComparators = new LinkedList<>();
    ComparatorAggregate(){
        activeComparators.add(new DefaultComparator());
        activeComparators.add(new LengthComparator());
        activeComparators.add(new VocabularyComparator());
    }
    public void process(News news){
        activeComparators.forEach(
                comparator -> comparator.process(news)
        );
    }

    }

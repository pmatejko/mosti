package Comparator;


import com.google.inject.Inject;
import interfaces.IComparator;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorAggregate {
    private List<IComparator> activeComparators = new LinkedList<>();
    @Inject
    ComparatorAggregate(LengthComparator lengthComparator, VocabularyComparator vocabularyComparator){
        activeComparators.add(lengthComparator);
        activeComparators.add(vocabularyComparator);
    }
    public void process(News news){
        activeComparators.forEach(
                comparator -> comparator.process(news)
        );
    }

    }

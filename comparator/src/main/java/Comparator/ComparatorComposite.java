package Comparator;


import com.google.inject.Inject;
import interfaces.IComparator;
import model.News;

import java.util.LinkedList;
import java.util.List;

public class ComparatorComposite implements IComparator{
    private List<IComparator> activeComparators = new LinkedList<>();
    @Inject  //kompozyt  -> ma implementować IComparator bo kompozyt xD w guice dodać, żeby wyciągnął wszystkie implementacje oprócz tej.
            // wtedy jest o tyle spoko, że dodanie nowego comparatora nie musi onaczać zmiany tej klasy
    ComparatorComposite(LengthComparator lengthComparator, VocabularyComparator vocabularyComparator){
        activeComparators.add(lengthComparator);
        activeComparators.add(vocabularyComparator);
    }
    public boolean process(News news){   //zmienić na bool. ma wracać, czy zapisywać, czy nie
        return activeComparators.stream().anyMatch(
                comparator -> comparator.process(news)
        );
    }

    }

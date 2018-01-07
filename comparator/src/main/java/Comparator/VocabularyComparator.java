package Comparator;

import interfaces.IComparator;
import model.News;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class VocabularyComparator implements IComparator {

    private final int maxUniqueWords;

    public VocabularyComparator(int maxUniqueWords) {
        this.maxUniqueWords = maxUniqueWords;
    }

    @Override
    public boolean process(News news) {
        StringTokenizer stringTokenizer = new StringTokenizer(news.getContent());
        Set<String> uniqueWords = new HashSet<>();
        while (stringTokenizer.hasMoreElements()) {
            uniqueWords.add(stringTokenizer.nextToken());
        }
        return uniqueWords.size() <= maxUniqueWords;
    }


}

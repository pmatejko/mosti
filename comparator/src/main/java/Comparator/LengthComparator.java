package Comparator;

import interfaces.IComparator;
import model.ConditionType;
import model.News;

import java.util.StringTokenizer;


public class LengthComparator implements IComparator {
    private final int maxWords;

    public LengthComparator(int maxWords) {
        this.maxWords = maxWords;
    }

    @Override
    public boolean process(News news) {
        StringTokenizer stringTokenizer = new StringTokenizer(news.getContent());
        int wordsCount = stringTokenizer.countTokens();
        return wordsCount <= maxWords;
    }

}

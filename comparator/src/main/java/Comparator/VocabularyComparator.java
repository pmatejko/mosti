package Comparator;

import com.google.inject.Inject;
import interfaces.IComparator;
import interfaces.IConfigurableComparator;
import model.Condition;
import model.ConditionType;
import model.News;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class VocabularyComparator implements IConfigurableComparator {
    private static final ConditionType TYPE = ConditionType.VOCABULARY;
    private final int maxUniqueWords;
    private final boolean authorizedToProcess;

    public VocabularyComparator(int maxUniqueWords) {
        this.maxUniqueWords = maxUniqueWords;
        this.authorizedToProcess = true;
    }

    @Inject
    public VocabularyComparator() {
        this.maxUniqueWords = 0;
        this.authorizedToProcess = false;
    }

    @Override
    public boolean process(News news) {
        if (authorizedToProcess) {
            StringTokenizer stringTokenizer = new StringTokenizer(news.getContent());
            Set<String> uniqueWords = new HashSet<>();
            while (stringTokenizer.hasMoreElements()) {
                uniqueWords.add(stringTokenizer.nextToken());
            }
            return uniqueWords.size() <= maxUniqueWords && !uniqueWords.isEmpty();
        } else throw new IllegalStateException("s");
    }

    @Override
    public boolean supports(Condition condition) {
        return condition.getType() == TYPE;
    }

    @Override
    public IConfigurableComparator createFor(Condition condition) {
        return new VocabularyComparator(condition.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VocabularyComparator that = (VocabularyComparator) o;

        if (maxUniqueWords != that.maxUniqueWords) return false;
        return authorizedToProcess == that.authorizedToProcess;
    }

    @Override
    public int hashCode() {
        int result = maxUniqueWords;
        result = 31 * result + (authorizedToProcess ? 1 : 0);
        return result;
    }
}

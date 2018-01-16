package Comparator;

import interfaces.IConfigurableComparator;
import model.Condition;
import model.ConditionType;
import model.News;

import java.util.StringTokenizer;

public class LengthComparator implements IConfigurableComparator {
    private static final ConditionType TYPE = ConditionType.LENGTH;
    private final int maxWords;
    private final boolean authorizedToProcess;

    public LengthComparator(int maxWords) {
        this.maxWords = maxWords;
        this.authorizedToProcess = true;
    }

    public LengthComparator() {
        this.maxWords = 0;
        this.authorizedToProcess = false;
    }

    @Override
    public boolean process(News news) {
        if (authorizedToProcess) {
            StringTokenizer stringTokenizer = new StringTokenizer(news.getContent());
            int wordsCount = stringTokenizer.countTokens();
            return wordsCount <= maxWords && wordsCount>0;
        }else throw new IllegalStateException("s");
    }

    @Override
    public boolean supports(Condition condition) {
        return condition.getType() == TYPE;
    }

    @Override
    public IConfigurableComparator createFor(Condition condition) {
        return new LengthComparator(condition.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LengthComparator that = (LengthComparator) o;

        if (maxWords != that.maxWords) return false;
        return authorizedToProcess == that.authorizedToProcess;
    }

    @Override
    public int hashCode() {
        int result = maxWords;
        result = 31 * result + (authorizedToProcess ? 1 : 0);
        return result;
    }
}

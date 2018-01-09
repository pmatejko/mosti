package Comparator;

import interfaces.IConfigurableComparator;
import model.Condition;
import model.ConditionType;
import model.News;

import java.util.StringTokenizer;

public class LengthComparator implements IConfigurableComparator {
    private final ConditionType TYPE = ConditionType.VOCABULARY;
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
            return wordsCount <= maxWords;
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


}

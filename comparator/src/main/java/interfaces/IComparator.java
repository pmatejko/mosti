package interfaces;



import model.Condition;
import model.News;

public interface IComparator {
    boolean process(News news);
    boolean supports(Condition condition);

}

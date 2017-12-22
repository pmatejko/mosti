package interfaces;


import model.News;
import model.User;

public interface IComparator {
    void process();
    boolean isUsed();
    boolean isNew();

}

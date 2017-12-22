package interfaces;


import model.News;
import model.User;

public interface IComparator {
    void process();
    Iterable<User> assignUsersByKeywords();
    boolean isNew();
    void save();

}

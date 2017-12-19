package interfaces;


import model.News;
import model.User;

public interface IComparator {
    void process();
    Iterable<User> assignUsersByPreferences();
    boolean isNew();
    void save();

}

package dao;

import model.News;
import model.User;

import java.util.List;

public interface UserDao {


    List<User> getUsersToNotify();
    void save(final User object);
    void update(final User object);
    List<News> getNewsToSend(User user);
}

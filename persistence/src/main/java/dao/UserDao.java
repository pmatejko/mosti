package dao;

import model.News;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    List<User> getUsersToNotify();
    void save(final User object);
    void update(final User object);
    List<News> getNewsToSend(User user);
    Optional<User> findUser(String email);
}

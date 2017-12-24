package dao;

import model.News;
import model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> create(String name, Date interval);

    Iterable<News> getNews(User user);

    List<User> getUsersToNotify();

    List<News> getNewsToSend(User user);
}

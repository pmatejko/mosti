package dao;

import model.News;
import model.User;

import java.util.List;

public interface UserDao {


    List<User> getUsersToNotify();

    List<News> getNewsToSend(User user);
}

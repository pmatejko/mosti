package dao;

import model.User;

import java.util.List;

public interface UserDao {


    List<User> getUsersToNotify();

    List getNewsToSend(User user);
}

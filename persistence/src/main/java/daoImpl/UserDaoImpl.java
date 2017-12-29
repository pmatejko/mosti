package daoImpl;


import dao.GenericDao;
import dao.UserDao;
import model.News;
import model.User;

import java.util.List;

public class UserDaoImpl extends GenericDao<User> implements UserDao {

    @Override
    public List<User> getUsersToNotify() {
        return null;
    }

    public List<News> getNewsToSend(User user) {
        return null;
    }

}

package daoImpl;

import dao.GenericDao;
import dao.UserDao;
import model.News;
import model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends GenericDao<User> implements UserDao{

    public Optional<User> create(String name, Date interval) {
        return null;
    }

    public Iterable<News> getNews(User user){
        return null;
    }

    @Override
    public List<User> getUsersToNotify() {
        return null;
    }

    public List<News> getNewsToSend(User user){
        return null;
    }

}

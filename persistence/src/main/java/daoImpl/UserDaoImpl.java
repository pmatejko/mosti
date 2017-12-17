package daoImpl;

import model.News;
import model.User;

import java.util.Date;
import java.util.Optional;

public class UserDao extends GenericDao<User> {

    public Optional<User> create(String name, Date interval) {
        return null;
    }

    public Iterable<News> getNews(User user){
        return null;
    }

    public Iterable<News> getNewsToSend(User user){
        return null;
    }






}

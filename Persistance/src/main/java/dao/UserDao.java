package dao;

import model.Article;
import model.Tweet;
import model.User;

import java.util.Date;
import java.util.Optional;

public class UserDao extends GenericDao<User> {

    public Optional<User> create(String name, Date interval) {
        return null;
    }

    public Optional<User> getUserById(long userID){
        return null;
    }

    public Iterable<Tweet> getTweetsToSend(User user){
    return null;
    }

    public Iterable<Tweet> getTweetsToSend(long userId){
        return null;
    }

    public Iterable<Tweet> getTweets(User user){
        return null;
    }

    public Iterable<Article> getArticles(User user){
        return null;
    }

    public Iterable<Article> getArticlesToSend(User user){
        return null;
    }

    public Iterable<Article> getArticlesToSend(long userId){
        return null;
    }




}

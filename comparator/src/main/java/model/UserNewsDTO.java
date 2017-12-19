package model;


import java.util.LinkedList;
import java.util.List;


public class UserNewsDTO {
    private final User user;
    private final List<News> newsList;

    public UserNewsDTO(User user, List<News> newsList) {
        this.user = user;
        this.newsList = newsList;
    }

    public UserNewsDTO(User user) {
        this.user = user;
        this.newsList = new LinkedList<>();
    }

    public void addNews(News news){
        newsList.add(news);
    }

    public User getUser() {
        return user;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}

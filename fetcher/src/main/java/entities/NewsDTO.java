package entities;

import java.util.List;
import model.News;

public class UserNews {
    private List<News> newsList;


    public UserNews(Long userId, List<News> newsList) {
        this.newsList = newsList;
    }


    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}

package entities;

import java.util.List;
import model.Article;
import model.News;
import model.Tweet;

public class UserNews {
    private Long userId;
    private List<News> newsList;


    public UserNews(Long userId, List<News> articleList) {
        this.userId = userId;
        this.newsList = articleList;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}

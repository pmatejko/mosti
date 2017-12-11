package entities;

import java.util.List;
import model.Article;
import model.Tweet;

public class UserNews {
    private Long userId;
    private List<Article> articleList;
    private List<Tweet> tweetList;


    public UserNews(Long userId, List<Article> articleList, List<Tweet> tweetList) {
        this.userId = userId;
        this.articleList = articleList;
        this.tweetList = tweetList;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }
}

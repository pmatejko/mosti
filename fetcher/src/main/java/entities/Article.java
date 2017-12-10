package entities;

import java.time.LocalDate;

public class Article extends NewsPiece {
    private String title;
    private String newsSite;


    public Article(String title, String newsSite, String url, String content, LocalDate timestamp) {
        super(url, content, timestamp);
        this.title = title;
        this.newsSite = newsSite;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsSite() {
        return newsSite;
    }

    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }
}

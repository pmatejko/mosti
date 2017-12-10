package entities;

import java.time.LocalDate;

public class Article extends NewsPiece {
    private String title;


    public Article(String title, String url, String content, LocalDate timestamp) {
        super(url, content, timestamp);
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package entities;

import java.time.LocalDate;

public class Tweet extends NewsPiece {
    private String author;


    public Tweet(String author, String url, String content, LocalDate timestamp) {
        super(url, content, timestamp);
        this.author = author;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

package model;

import java.util.Date;

public class Article {
    private String title;
    private String newsSite;
    private String url;
    private String content;
    private Date timestamp;


    public Article(String title, String newsSite, String url, String content, Date timestamp) {
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
        this.title = title;
        this.newsSite = newsSite;
    }



}


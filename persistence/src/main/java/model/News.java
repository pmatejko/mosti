package model;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = News.TABLE_NAME)
public class News {
    public static final String TABLE_NAME = "news";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.NEWSSITE, nullable = false)
    private String newsSite;

    @Column(name = Columns.URL, nullable = false)
    private String url;

    @Column(name = Columns.CONTENT)
    private String content;

    @Column(name = Columns.TIMESTAMP, nullable = false)
    private Date timestamp;

    @ManyToMany
    @JoinTable(
            name = "keyword_news",
            joinColumns = @JoinColumn(name = Columns.ID, referencedColumnName = Keyword.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = Keyword.Columns.ID, referencedColumnName = Columns.ID)
    )
    private List<Keyword> keywords = new LinkedList<>();

    public News() {

    }


    public News(String title, String newsSite, String url, String content, Date timestamp) {
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
        this.newsSite = newsSite;
    }

    public long getId() { return id; }

    public String getNewsSite() {
        return newsSite;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void setKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }

    public String toOutputFormat() {
        return this.toString();
    }


    public static class Columns {
        public static final String ID = "id";
        public static final String NEWSSITE = "newsSite";
        public static final String URL = "url";
        public static final String CONTENT = "content";
        public static final String TIMESTAMP = "timestamp";
    }
}




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

    @Column(name = Columns.PREFERENCES)
    @ManyToMany(mappedBy = Preferences.NEWS_PREFERENCES_JUNCTION_TABLE_NAME)
    private List<Preferences> preferences = new LinkedList<>();

    @Column(name = Columns.URL, nullable = false)
    private String url;

    @Column(name = Columns.CONTENT, nullable = false)
    private String content;

    @Column(name = Columns.TIMESTAMP, nullable = false)
    private Date timestamp;


    public News() {
    }

    public News(Preferences preferences, String url, String content, Date timestamp) {
        this.preferences.add(preferences);
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
    }


    public long getId() { return id; }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setId(long id) {
        this.id = id;
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


    public boolean hasKeyword(){
        return preferences.get(0).getKeyword() != null;
    }

    public String toOutputFormat() {
        return this.toString();
    }


    public static class Columns {
        public static final String ID = "id";
        public static final String PREFERENCES = "preferences";
        public static final String URL = "url";
        public static final String CONTENT = "content";
        public static final String TIMESTAMP = "timestamp";
    }
}




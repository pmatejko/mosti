package model;

import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;



@Entity
@Table(name = News.TABLE_NAME)
public class News {
    public static final String TABLE_NAME = "news";
    public static final String NEWS_PREFERENCES_JUNCTION_TABLE_NAME = "news_preferences";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = NEWS_PREFERENCES_JUNCTION_TABLE_NAME,
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = News.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = "preferences_id", referencedColumnName = Preferences.Columns.ID)
    )
    private List<Preferences> preferences = new LinkedList<>();


    @Column(name = Columns.URL, nullable = false,length = 1024)
    private String url;

    @Column(name = Columns.CONTENT, nullable = false,length = 4096)
    private String content;

    @Column(name = Columns.TIMESTAMP, nullable = false)
    private Timestamp timestamp;


    public News() {
    }

    public News(Preferences preferences, String url, String content, Timestamp timestamp) {
        this.preferences.add(preferences);
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
    }

    public List<Preferences> getPreferences() {
        return preferences;
    }

    public News(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public void addPreference(Preferences preference){
        preferences.add(preference);
    }

    public long getId() { return id; }

    public void setPreferences(List<Preferences> preferences) {
        this.preferences = preferences;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean hasKeyword(){
        return preferences.get(0).getKeyword() != null;
    }

    public String toOutputFormat() {
        return this.toString();
    }

    @Override
    public String toString() {
        Preferences p = preferences.get(0);
        return "News Source: " + p.getNewsSource()
                + ", Keyword: " + p.getKeyword() + "\n"
                + url + "\n"
                + timestamp + "\n"
                + content;
    }

    public static class Columns {
        public static final String ID = "news_id";
        public static final String PREFERENCES = "preferences";
        public static final String URL = "url";
        public static final String CONTENT = "content";
        public static final String TIMESTAMP = "timestamp";


    }
}




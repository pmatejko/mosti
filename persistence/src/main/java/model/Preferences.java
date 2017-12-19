package model;

import fetcher.DataProvider;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = Preferences.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = {Preferences.Columns.NEWS_SOURCE, Preferences.Columns.KEYWORD, Preferences.Columns.DATA_PROVIDER})
})
public class Preferences {
    public static final String TABLE_NAME = "preferences";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.KEYWORD)
    private String keyword;

    @Column(name = Columns.NEWS_SOURCE)
    private String newsSource;

    @Column(name = Columns.DATA_PROVIDER)
    @Enumerated(EnumType.STRING)
    private DataProvider dataProvider;

    @ManyToMany
    @JoinTable(
            name = "user_preferences",
            joinColumns = @JoinColumn(name = Preferences.Columns.ID, referencedColumnName = User.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = User.Columns.ID, referencedColumnName = Preferences.Columns.ID)
    )
    private List<User> users = new LinkedList<>();


    public Preferences() {
    }

    public Preferences(String newsSource, DataProvider dataProvider) {
        this.newsSource = newsSource;
        this.dataProvider = dataProvider;
    }


    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getNewsSource() { return newsSource; }

    public void setNewsSource(String newsSource) { this.newsSource = newsSource; }

    public DataProvider getDataProvider() { return dataProvider; }

    public void setDataProvider(DataProvider dataProvider) { this.dataProvider = dataProvider; }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public static class Columns {
        public static final String ID = "id";
        public static final String KEYWORD = "keyword";
        public static final String NEWS_SOURCE = "news_source";
        public static final String DATA_PROVIDER = "data_provider";
    }

}

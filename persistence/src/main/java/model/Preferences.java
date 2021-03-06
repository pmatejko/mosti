package model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = Preferences.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = {Preferences.Columns.NEWS_SOURCE, Preferences.Columns.KEYWORD, Preferences.Columns.DATA_PROVIDER})
})
public class Preferences {
    public static final String TABLE_NAME = "preferences";

    public static final String USER_PREFERENCES_JUNCTION_TABLE_NAME = "user_preferences";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.KEYWORD)
    private String keyword;

    // for example BBC, Bloomberg
    @Column(name = Columns.NEWS_SOURCE,length = 1024)
    private String newsSource;

    // for example newsApi or Twitter
    @Column(name = Columns.DATA_PROVIDER)
    @Enumerated(EnumType.STRING)
    private DataProvider dataProvider;

    //@ManyToMany(cascade = {CascadeType.ALL})

    //private List<News> news = new LinkedList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = USER_PREFERENCES_JUNCTION_TABLE_NAME,
            joinColumns = @JoinColumn(name ="preferences_id", referencedColumnName = Preferences.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = User.Columns.ID)
    )
    private List<User> users = new LinkedList<>();


    public Preferences() {
    }

    public Preferences(String keyword, String newsSource, DataProvider dataProvider) {
        this.keyword = keyword;
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

//    public List<News> getNews() {
//        return news;
//    }

//    public void setNews(List<News> news) {
//        this.news = news;
//    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public  void addUser(User user){
        users.add(user);
    }

//    public void addNews(News news) {this.news.add(news); }


    @Override
    public String toString() {
        return "Preferences{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", newsSource='" + newsSource + '\'' +
                ", dataProvider=" + dataProvider +
                '}';
    }

    public static class Columns {
        public static final String ID = "preferences_id";
        public static final String KEYWORD = "keyword";
        public static final String NEWS_SOURCE = "news_source";
        public static final String DATA_PROVIDER = "data_provider";
    }

}

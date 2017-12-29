package model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = model.CompareType.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = model.CompareType.Columns.TYPE)
})
public class CompareType {
    public static final String TABLE_NAME = "compare_type";
    public static final String COMPARE_TYPE_NEWS_JUNCTION_TABLE_NAME = "compare_type_news";
    public static final String COMPARE_TYPE_USERS_JUNCTION_TABLE_NAME = "compare_type_users";



    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = model.CompareType.Columns.ID)
    private long id;

    @Column(name = model.CompareType.Columns.TYPE, nullable = false)
    private String type;

    @ManyToMany
    @JoinTable(
            name = COMPARE_TYPE_USERS_JUNCTION_TABLE_NAME,
            joinColumns = @JoinColumn(name = "compare_type_id", referencedColumnName = CompareType.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = "news_id", referencedColumnName = News.Columns.ID)
    )
    private List<News> news = new LinkedList<>();


    @ManyToMany
    @JoinTable(
            name = COMPARE_TYPE_NEWS_JUNCTION_TABLE_NAME,
            joinColumns = @JoinColumn(name = "compare_type_id", referencedColumnName = CompareType.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = User.Columns.ID)
    )
    private List<User> users = new LinkedList<>();

    public CompareType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public long getId() {
        return id;
    }
    public  void addNews(News n){
        news.add(n);
    }
     public  void addUser(User u){
        users.add(u);
     }

    public static class Columns {
        public static final String ID = "compare_type_id";
        public static final String TYPE = "type";
    }
}


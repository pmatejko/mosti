package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = Keyword.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Keyword.Columns.WORD)
})
public class Keyword {
    public static final String TABLE_NAME = "keyword";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.WORD, nullable = false)
    private String word;


    @ManyToMany(mappedBy = "keywords")
    private List<News> news = new LinkedList<>();


    @ManyToMany
    @JoinTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = Columns.ID, referencedColumnName = User.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = User.Columns.ID, referencedColumnName = Columns.ID)
    )
    private List<User> users = new LinkedList<>();

    public long getId() { return id; }

    public String getWord() {
        return word;
    }

    public List<News> getNews() {
        return news;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setArticle(News news) {
        this.news.add(news);
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    public Keyword() {
    }


    public static class Columns {
        public static final String ID = "id";
        public static final String WORD = "word";
    }
}

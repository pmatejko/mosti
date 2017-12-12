package model;

import javax.persistence.*;
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
    private Set<Article> articles;


    @ManyToMany
    @JoinTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = Columns.ID, referencedColumnName = User.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = User.Columns.ID, referencedColumnName = Columns.ID)
    )
    private Set<User> users;


    public String getWord() {
        return word;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setArticle(Article article) {
        this.articles.add(article);
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

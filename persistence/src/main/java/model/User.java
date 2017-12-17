package model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = User.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.Columns.EMAIL)
})
public class User {
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.EMAIL, nullable = false)
    private String email;

    @Column(name = Columns.INTERVAL, nullable = false)
    private Date interval;


    @OneToMany(mappedBy = "user")
    private List<Preferences> preferences= new LinkedList<>();

    @ManyToMany(mappedBy = "users")
    private List<Keyword> keywords = new LinkedList<>();


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public Date getInterval() {
        return interval;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public long getId() { return id; }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void setKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }


    public static class Columns {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String INTERVAL = "interval";
    }
}

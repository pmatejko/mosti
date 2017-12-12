package model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "users")
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Keyword> keywords = new HashSet<>();


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public Date getInterval() {
        return interval;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void setTag(Tag tag) {
        this.tags.add(tag);
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

package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = Tag.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Tag.Columns.TAGNAME)
})
public class Tag {
    public static final String TABLE_NAME = "tag";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;


    @Column(name = Columns.TAGNAME, nullable = false)
    private String tagName;


    @ManyToMany(mappedBy = "tags")
    private Set<Tweet> tweets;

    @ManyToMany
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name = Columns.ID, referencedColumnName = User.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = User.Columns.ID, referencedColumnName = Columns.ID)
    )
    private Set<User> users;

    public Tag() {
    }

    public String getTagName() {
        return tagName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    public void setUser(User user) {
        this.users.add(user);
    }


    public static class Columns {
        public static final String ID = "id";
        public static final String TAGNAME = "tag_name";
    }
}

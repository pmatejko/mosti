package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = Tweet.TABLE_NAME)
public class Tweet  {
    public static final String TABLE_NAME = "tweet";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.AUTHOR,nullable = false)
    private String author;

    @Column(name = Columns.URL, nullable = false)
    private String url;

    @Column(name=Columns.CONTENT)
    private String content;//? n pewno

    @Column(name=Columns.TIMESTAMP,nullable = false)
    private Date timestamp;

    public Set<Tag> getTags() {
        return tags;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @ManyToMany
    @JoinTable(
            name = "tag_tweet",

            joinColumns= @JoinColumn(name = Columns.ID, referencedColumnName = Tag.Columns.ID),
            inverseJoinColumns = @JoinColumn(name = Tag.Columns.ID, referencedColumnName = Columns.ID)
    )
    private Set<Tag> tags;

    public Tweet() {
    }

    public Tweet(String author, String url, String content, Date timestamp) {
        this.author = author;
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
    }


    public static class Columns{
        public static final String ID = "id";
        public static final String AUTHOR = "author";
        public static final String URL= "url";
        public static final String CONTENT= "content"; // na pewno?
        public static final String TIMESTAMP = "timestamp";
    }
}
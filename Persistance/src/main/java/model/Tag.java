package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = Tag.TABLE_NAME)
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


    public static class Columns {
        public static final String ID = "id";
        public static final String TAGNAME = "tag_name";
    }
}

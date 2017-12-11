package model;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.EMAIL, nullable = false)
    private String email;

    @Column(name = Columns.INTERVAL,nullable = false)
    private Date interval;

    @ManyToMany(mappedBy = "users")
    private Set<Tag> tags;

    public User() {
    }



    public static class Columns{
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String INTERVAL= "interval";
    }
}

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

    @ManyToMany(mappedBy = "users")
    private List<Preferences> preferences = new LinkedList<>();


    public User() {
    }


    public long getId() { return id; }

    public String getEmail() {
        return email;
    }

    public Date getInterval() {
        return interval;
    }

    public List<Preferences> getPreferences() {
        return preferences;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public void setPreferences(List<Preferences> preferences) {
        this.preferences = preferences;
    }

    public void addPreferences(Preferences preferences) {
        this.preferences.add(preferences);
    }

    public void removePreferences(Preferences preferences) {
        this.preferences.remove(preferences);
    }



    public static class Columns {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String INTERVAL = "interval";
    }
}

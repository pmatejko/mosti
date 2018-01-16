package model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = User.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.Columns.EMAIL)
})
public class User {
    public static final String TABLE_NAME = "subscriber";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.EMAIL, nullable = false)
    private String email;

    @Column(name = Columns.LAST_NOTIFICATION, nullable = false)
    private Timestamp lastNotification;

    @Column(name = Columns.INTERVAL, nullable = false)
    private int interval;

    @ManyToMany(mappedBy ="users",cascade = {CascadeType.ALL})
    private List<Preferences> preferences = new LinkedList<>();

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<Condition> conditions = new LinkedList<>();


    public User() {
        this.lastNotification= new Timestamp(System.currentTimeMillis());

    }


    public long getId() { return id; }

    public String getEmail() {
        return email;
    }

    public int getInterval() {
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

    public void setInterval(int interval) {
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

    public void setConditions(List<Condition> conditions){this.conditions = conditions;}

    public void addCompareType(Condition condition){
        conditions.add(condition);}

    public void removeCompareType(Condition condition){this.conditions.remove(condition);}

    public void setLastNotification(Timestamp lastNotification) {
        this.lastNotification = lastNotification;
    }

    public Timestamp getLastNotification() {
        return lastNotification;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", lastNotification=" + lastNotification +
                ", interval=" + interval +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void addCondition(Condition condition) {conditions.add(condition);
    }

    public static class Columns {
        public static final String ID = "user_id";
        public static final String EMAIL = "email";
        public static final String INTERVAL = "interval";
        public static final String LAST_NOTIFICATION="last_notification";
    }
}

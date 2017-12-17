package model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table(name = Preferences.TABLE_NAME)
public class Preferences {
    public static final String TABLE_NAME = "preferences";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Columns.ID)
    private long id;

    @Column(name = Columns.DATA_SOURCE)
    private String data_source;

    @Column(name = Columns.USER_ID)
    private long user_id;

    @Column(name = Columns.DATA_PROVIDER)
    private String data_provider;

    @ManyToOne
    @JoinColumn(name = User.Columns.ID)
    private User user;

    public Preferences() {
    }

    public Preferences(String data_source, long user_id, String data_provider) {
        this.data_source = data_source;
        this.user_id = user_id;
        this.data_provider = data_provider;
    }

    public long getId() { return id; }

    public String getData_source() { return data_source; }

    public void setData_source(String data_source) { this.data_source = data_source; }

    public long getUser_id() { return user_id; }

    public void setUser_id(long user_id) { this.user_id = user_id; }

    public String getData_provider() { return data_provider; }

    public void setData_provider(String data_provider) { this.data_provider = data_provider; }

    public static class Columns {
        public static final String ID = "id";
        public static final String DATA_SOURCE = "data_source";
        public static final String USER_ID = "user_id";
        public static final String DATA_PROVIDER = "data_provider";
    }

}

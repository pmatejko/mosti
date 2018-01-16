package model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = Condition.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Condition.Columns.TYPE)
})
public class Condition {
    public static final String TABLE_NAME = "condition";
    public static final String CONDITION_USERS_JUNCTION_COLUMN_NAME = "conditions_user";



    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = Condition.Columns.ID)
    private long id;


    @Column(name = Condition.Columns.TYPE, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionType type;

    @Column(name = Columns.VALUE, nullable = false)
    private int value;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = CONDITION_USERS_JUNCTION_COLUMN_NAME
    )
    private User user;

    public Condition() {
    }

    public ConditionType getType() {
        return type;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition that = (Condition) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public static class Columns {
        public static final String ID = "condition_id";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
    }
}


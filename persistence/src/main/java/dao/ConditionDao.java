package dao;

import model.Condition;

public interface ConditionDao {
    Condition getConditionByName(String type);
    void update(Condition condition);
    void save(Condition condition);
}

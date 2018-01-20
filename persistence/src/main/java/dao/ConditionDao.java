package dao;

import model.Condition;

public interface ConditionDao {
    Condition getConditionByName(String type);
    void update(Condition condition);
    void delete(Long id);
    void save(Condition condition);
}

package dao;

import model.Condition;

public interface CompareTypeDao {
    Condition getCompareTypeByName(String type);
    void update(Condition condition);
    void save(Condition condition);
}

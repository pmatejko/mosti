package dao;

import com.sun.glass.ui.CommonDialogs;
import model.CompareType;
import model.News;

public interface CompareTypeDao {
    CompareType getCompareTypeByName(String type);
    void update(CompareType compareType);
    void save(CompareType compareType);
}

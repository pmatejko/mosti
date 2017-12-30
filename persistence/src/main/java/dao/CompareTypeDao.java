package dao;

import com.sun.glass.ui.CommonDialogs;
import model.CompareType;
import model.News;

public interface CompareTypeDao {
    CompareType getCompareTypeByName(String type);
    void bind(CompareType compareType, News news);
    void update(CompareType compareType);
    void save(CompareType compareType);
}

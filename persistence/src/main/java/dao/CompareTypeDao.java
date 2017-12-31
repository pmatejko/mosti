package dao;

import com.sun.glass.ui.CommonDialogs;
import model.CompareType;
import model.News;
import org.hibernate.Session;

public interface CompareTypeDao {
    CompareType getCompareTypeByName(String type);
    void bind(CompareType compareType, News news);
    void update(CompareType compareType);
    void save(CompareType compareType);
}

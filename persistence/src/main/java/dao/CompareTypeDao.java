package dao;

import com.sun.glass.ui.CommonDialogs;
import model.CompareType;

public interface CompareTypeDao {
    CompareType getCompareTypeByName(String type);
    void update(CompareType compareType);
}

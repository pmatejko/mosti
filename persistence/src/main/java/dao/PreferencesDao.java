package dao;


import model.DataProvider;
import model.Preferences;

import java.util.Optional;

public interface PreferencesDao {
    Optional<Preferences> findPreferences(String keyword, String newsSource, DataProvider dataProvider);

    void save(Preferences preferences);

    void update(Preferences preferences);

}

package dao;


import model.DataProvider;
import model.Preferences;

import java.util.List;
import java.util.Optional;

public interface PreferencesDao {
    Optional<Preferences> findPreferences(String keyword, String newsSource, DataProvider dataProvider);
    void delete(Long id);
    void save(Preferences preferences);
    void update(Preferences preferences);
    List<Preferences> getAllPreferences();
}

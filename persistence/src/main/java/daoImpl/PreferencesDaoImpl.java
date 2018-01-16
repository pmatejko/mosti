package daoImpl;

import dao.GenericDao;
import dao.PreferencesDao;
import model.DataProvider;
import model.Preferences;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PreferencesDaoImpl extends GenericDao<Preferences> implements PreferencesDao {
    public Optional<Preferences> findPreferences(String keyword, String newsSource, DataProvider dataProvider){
        final Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Preferences> preference = session
                .createQuery("from Preferences p where p.keyword=:k and p.dataProvider=:d and p.newsSource=:n",Preferences.class)
                .setParameter("k", keyword)
                .setParameter("d",dataProvider)
                .setParameter("n",newsSource)
                .getResultList();
        session.getTransaction().commit();
        if(preference.isEmpty())
        return Optional.empty();

        return Optional.of(preference.get(0));

    }
}

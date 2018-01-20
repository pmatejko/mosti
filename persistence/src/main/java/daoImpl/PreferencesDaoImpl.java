package daoImpl;

import dao.GenericDao;
import dao.PreferencesDao;
import model.DataProvider;
import model.Preferences;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public void delete(Long id) {
        try (final Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            int result = session.createQuery("delete Preferences where id= :id")
                    .setParameter("id",id)
                    .executeUpdate();
            tx.commit();
            if (result > 0) {
                System.out.println("Selected preferences removed");
            }
        }
    }

    @Override
    public List<Preferences> getAllPreferences() {
        final Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        List<Preferences> preferences = session
                .createQuery("from Preferences p", Preferences.class)
                .getResultList();
        session.getTransaction().commit();

        return preferences;
    }
}

package daoImpl;

import dao.CompareTypeDao;
import dao.GenericDao;
import model.CompareType;
import model.News;
import org.hibernate.Session;

import javax.persistence.PersistenceException;

public class CompareTypeDaoImpl extends GenericDao<CompareType> implements CompareTypeDao {
    public CompareType getCompareTypeByName(String type) {
        try (final Session session = sessionFactory.openSession()) {
        return session
                .createQuery("from CompareType c where c.type=:compare_type",CompareType.class)
                .setParameter("compare_type",type)
                .getSingleResult();
        }
    }

    @Override
    public void bind(CompareType compareType, News news) {
        try (final Session session = sessionFactory.openSession()) {
            news.addCompareType(compareType);
        }
        save(compareType);
    }

    @Override
    public void update(CompareType object) throws PersistenceException {
        super.update(object);
    }
}

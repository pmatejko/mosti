package daoImpl;

import dao.CompareTypeDao;
import dao.GenericDao;
import model.CompareType;
import org.hibernate.Session;

import javax.persistence.PersistenceException;

public class CompareTypeDaoImpl extends GenericDao<CompareType> implements CompareTypeDao {
    public CompareType getCompareTypeByName(String type) {
        final Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompareType compareType = session
                .createQuery("from CompareType c where c.type=:compare_type", CompareType.class)
                .setParameter("compare_type", type)
                .getSingleResult();
        session.getTransaction().commit();
        return compareType;

    }


    @Override
    public void update(CompareType object) throws PersistenceException {
        super.update(object);
    }
}

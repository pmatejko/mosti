package daoImpl;

import dao.CompareTypeDao;
import dao.GenericDao;
import model.CompareType;

import javax.persistence.PersistenceException;

public class CompareTypeDaoImpl extends GenericDao<CompareType> implements CompareTypeDao {
    public CompareType getCompareTypeByName(String type) {
        return sessionFactory.openSession()
                .createQuery("from CompareType c where c.type=:compare_type",CompareType.class)
                .setParameter("compare_type",type)
                .getSingleResult();
    }

    @Override
    public void update(CompareType object) throws PersistenceException {
        super.update(object);
    }
}

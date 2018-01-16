package daoImpl;

import dao.ConditionDao;
import dao.GenericDao;
import model.Condition;
import org.hibernate.Session;

import javax.persistence.PersistenceException;

public class ConditionDaoImpl extends GenericDao<Condition> implements ConditionDao {
    public Condition getConditionByName(String type) {
        final Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Condition condition = session
                .createQuery("from Condition c where c.type=:compare_type", Condition.class)
                .setParameter("compare_type", type)
                .getSingleResult();
        session.getTransaction().commit();
        return condition;

    }


}

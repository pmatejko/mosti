package daoImpl;

import dao.ConditionDao;
import dao.GenericDao;
import model.Condition;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public void delete(Long id) {
        try (final Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            int result = session.createQuery("delete Condition where id= :id")
                    .setParameter("id",id)
                    .executeUpdate();
            tx.commit();
            if (result > 0) {
                System.out.println("Selected conditions removed");
            }
        }
    }


}

package daoImpl;


import dao.GenericDao;
import dao.UserDao;
import model.News;
import model.User;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

public class UserDaoImpl extends GenericDao<User> implements UserDao {

    @Override
    public List<User> getUsersToNotify() {
        try (final Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM subscriber  u WHERE (ABS(DATE_PART('day', current_timestamp-u.last_notification  )) * 24 + \n" +
                    " ABS(DATE_PART('hour', current_timestamp- u.last_notification ))) * 60 +\n" +
                    " ABS(DATE_PART('minute',current_timestamp- u.last_notification ))>=u.interval";
            return session.createNativeQuery(sql, User.class).list();
        }
    }

    @Override
    public List<News> getNewsToSend(User user) {
        try (final Session session = sessionFactory.openSession()) {
            List<Long> preferences_ids = session.createQuery("select p.id from Preferences p join p.users u where u.id=:id ", Long.class)
                    .setParameter("id", user.getId()).list();

            List<News> newsList = session.createQuery("select n from News  n join n.preferences p where p.id in :id and n.timestamp>:l", News.class)
                    .setParameter("l", user.getLastNotification())
                    .setParameterList("id", preferences_ids).list();

            user.setLastNotification(new Timestamp(System.currentTimeMillis()));
            update(user);
            return newsList;
        }

    }


}

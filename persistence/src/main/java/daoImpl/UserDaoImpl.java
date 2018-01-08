package daoImpl;


import dao.GenericDao;
import dao.UserDao;
import model.CompareType;
import model.News;
import model.User;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl extends GenericDao<User> implements UserDao {

    @Override
    public List<User> getUsersToNotify() {
        try(final Session session=sessionFactory.openSession()){
        String sql = "SELECT * FROM subscriber  u WHERE (ABS(DATE_PART('day', current_timestamp-u.last_notification  )) * 24 + \n" +
                " ABS(DATE_PART('hour', current_timestamp- u.last_notification ))) * 60 +\n" +
                " ABS(DATE_PART('minute',current_timestamp- u.last_notification ))>=u.interval";
        return session.createNativeQuery(sql, User.class).list();}
    }
    @Override
    public List<News> getNewsToSend(User user) {
        try(final Session session=sessionFactory.openSession()){
        List<Long> preferences_ids = session.createQuery("select p.id from Preferences p join p.users u where u.id=:id ", Long.class)
                .setParameter("id", user.getId()).list();

     List<News>newsList=   session.createQuery("select n from Preferences  p join p.news n where p.id in :id and n.timestamp>:l", News.class)
                .setParameter("l", user.getLastNotification())
                .setParameterList("id", preferences_ids).list();
        List<CompareType> compareTypes = user.getCompareTypes();
        List<News> newsToSent = newsList.stream()
                .filter(news -> containsTypes(news, compareTypes))
                .collect(Collectors.toList());
        user.setLastNotification(new Timestamp(System.currentTimeMillis()));
        update(user);
       return newsToSent;}

    }
    private boolean containsTypes(News news, List<CompareType> compareTypes) {
        for (CompareType t : news.getCompareTypes())
            if (compareTypes.contains(t))
                return true;
        return false;
    }

}

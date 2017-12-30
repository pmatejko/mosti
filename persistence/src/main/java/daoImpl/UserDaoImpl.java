package daoImpl;


import dao.GenericDao;
import dao.UserDao;
import model.CompareType;
import model.News;
import model.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl extends GenericDao<User> implements UserDao {

    @Override
    public List<User> getUsersToNotify() {
        String sql = "SELECT * FROM subscriber  u WHERE (DATE_PART('day', current_timestamp-u.last_notification  ) * 24 + \n" +
                " DATE_PART('hour', current_timestamp- u.last_notification )) * 60 +\n" +
                " DATE_PART('minute',current_timestamp- u.last_notification )>=u.interval";
        return sessionFactory.openSession().createNativeQuery(sql, User.class).list();
    }

    public List getNewsToSend(User user) {
        List<Long> preferences_ids = sessionFactory.openSession().createQuery("select p.id from Preferences p join p.users u where u.id=:id ", Long.class)
                .setParameter("id", user.getId()).list();

     List<News>newsList=   sessionFactory.openSession().createQuery("select n from Preferences  p join p.news n where p.id in :id and n.timestamp>:l", News.class)
                .setParameter("l", user.getLastNotification())
                .setParameterList("id", preferences_ids).list();
        List<CompareType> compareTypes = user.getCompareTypes();
        List<News> newsToSent = newsList.stream()
                .filter(news -> containsTypes(news, compareTypes))
                .collect(Collectors.toList());
       return newsToSent;

    }

    private boolean containsTypes(News news, List<CompareType> compareTypes) {
        for (CompareType t : news.getCompareTypes())
            if (compareTypes.contains(t))
                return true;
        return false;
    }

}
//"select p from Conn c   inner join c.Patient  p  inner join c.Bill b  where b.Balance < :balance and p.FullName = 'John'
// /  from Cat as cat where cat.mate.name like '%s%'
// return sessionFactory.openSession()
//         .createQuery("from CompareType c where c.type=:compare_type",CompareType.class)
//        .setParameter("compare_type",type)
//        .getSingleResult();
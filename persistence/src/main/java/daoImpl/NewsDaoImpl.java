package daoImpl;


import dao.GenericDao;
import dao.NewsDao;
import model.News;
<<<<<<< HEAD
=======
import model.User;
import org.hibernate.Session;
>>>>>>> d43e0d853fd35d9947ee0fa4ef94b8c1730fbd09


import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
public class NewsDaoImpl extends GenericDao<News> implements NewsDao{
=======
public class NewsDaoImpl extends GenericDao<News> implements NewsDao {
>>>>>>> d43e0d853fd35d9947ee0fa4ef94b8c1730fbd09

    @Override
    public void openSession() {
        sessionFactory.openSession();
    }

    @Override
    public News getOrCreate(News news) {
        Optional<News> matchingNews = findByUrl(news);
        if (!matchingNews.isPresent()) {
            // no news has the same url, so we have a new one
            save(news);
            return news;
        } else {
            News existingNews = matchingNews.get();
            // fetcher sends the same article many times with diffrent keywords,
            // so we add new keywords to existing ones.
            news.getPreferences().forEach(existingNews::addPreference);
            update(existingNews);
            return existingNews;

        }
    }

    @Override
    public Optional<News> findByUrl(News news) {
        final Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<News> newsList = session
                .createQuery("from News  n where n.url = :url", News.class)
                .setParameter("url", news.getUrl())
                .list();
        session.getTransaction().commit();
        if (newsList.isEmpty())
            return Optional.empty();
        else return Optional.of(newsList.get(0));
    }

}

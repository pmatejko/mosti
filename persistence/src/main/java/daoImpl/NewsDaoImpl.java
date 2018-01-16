package daoImpl;


import dao.GenericDao;
import dao.NewsDao;
import model.News;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Optional;


public class NewsDaoImpl extends GenericDao<News> implements NewsDao {

    @Override
    public void openSession() {
        sessionFactory.openSession();
    }

    @Override
    public News updateOrCreate(News news) {
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
        try (final Session session = sessionFactory.openSession()) {

            final Transaction tx = session.beginTransaction();
            List<News> newsList = session
                    .createQuery("from News  n where n.url = :url", News.class)
                    .setParameter("url", news.getUrl())
                    .list();
            tx.commit();
            if (newsList.isEmpty())
                return Optional.empty();
            else return Optional.of(newsList.get(0));
        }
    }

}

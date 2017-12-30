package daoImpl;


import dao.GenericDao;
import dao.NewsDao;
import model.News;
import model.User;
import org.hibernate.Session;


import java.util.List;
import java.util.Optional;

public class NewsDaoImpl extends GenericDao<News> implements NewsDao {

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
        try (final Session session = sessionFactory.openSession()) {
        List<News> newsList = session
                .createQuery("from News  n where n.url = :url", News.class)
                .setParameter("url", news.getUrl())
                .list();
        if (newsList.isEmpty())
            return Optional.empty();
        else return Optional.of(newsList.get(0));
    }}

}

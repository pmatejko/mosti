package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    void openSession();
    News updateOrCreate(News news);
    void save(News news);
    void update(News news);
    Optional<News>findByUrl(News news);

}

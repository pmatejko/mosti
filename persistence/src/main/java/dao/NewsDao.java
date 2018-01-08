package dao;

import model.News;

import java.util.Optional;

public interface NewsDao {
    void openSession();
    News getOrCreate(News news);
    void save(News news);
    void update(News news);
    Optional<News>findByUrl(News news);

}

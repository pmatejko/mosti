package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    News getOrCreate(News news);
    void save(News news);
    void update(News news);
    Optional<News>findByUrl(News news);

}

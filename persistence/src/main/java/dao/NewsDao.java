package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    News getOrCreate(News news);

    Optional<News>findByUrl(News news);

}

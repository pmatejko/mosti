package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    Optional<News> create(News news);

    Iterable<News> findByUrl(News news);


    boolean isNew(News news);

    boolean isUsed(News news);
}

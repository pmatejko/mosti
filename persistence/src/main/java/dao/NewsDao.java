package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    public Optional<News> create(News news);

    Iterable<News> findByUrl(News news);


    public boolean isNew(News news);

    boolean isUsed(News news);
}

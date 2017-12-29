package dao;

import model.News;
import model.User;

import java.util.Optional;

public interface NewsDao {
    News getOrCreate(News news);

    Iterable<News> findByUrl(News news);


    boolean isNew(News news);

    boolean isUsed(News news);
}

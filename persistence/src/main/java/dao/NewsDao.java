package dao;

import model.News;

import java.util.Optional;

public interface NewsDao {
    public Optional<News> create();


    public boolean isNew(News news);
}

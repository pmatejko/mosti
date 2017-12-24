package dao.impl;


import dao.GenericDao;
import dao.NewsDao;
import model.News;

import java.util.Optional;

public class NewsDaoImpl extends GenericDao<News> implements NewsDao{

    @Override
    public Optional<News> create(News news) {
        return null;
    }

    @Override
    public Iterable<News> findByUrl(News news) {
        return null;
    }


    public boolean isNew(News news) {
        return false;
    }

    @Override
    public boolean isUsed(News news) {
        return false;
    }
}

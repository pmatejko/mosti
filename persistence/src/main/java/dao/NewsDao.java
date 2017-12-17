package dao;

import model.News;

import java.util.Optional;

public class NewsDao extends GenericDao{
    public Optional<News> create() {//parametry potem
        return null;
    }


    public boolean isNew(News news) {
        return false;
    }
}

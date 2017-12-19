package daoImpl;

import dao.GenericDao;
import dao.NewsDao;
import model.News;

import java.util.Optional;

public class NewsDaoImpl extends GenericDao implements NewsDao{
    public Optional<News> create() {//parametry potem
        return null;
    }


    public boolean isNew(News news) {
        return false;
    }
}

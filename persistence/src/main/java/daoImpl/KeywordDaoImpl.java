package daoImpl;

import dao.GenericDao;
import dao.KeywordDao;
import model.News;
import model.Keyword;

import java.util.Optional;

public class KeywordDaoImpl extends GenericDao<Keyword> implements KeywordDao{
    public Optional<Keyword> create(String name) {
        return null;
    }

    public Optional<Keyword> findKeywordByName(String name){
        return null;
    }

    public Iterable<News> getNews(Keyword keyword){
        return null;
    }

}

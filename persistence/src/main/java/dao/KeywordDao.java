package dao;

import model.News;
import model.Keyword;

import java.util.Optional;

public class KeywordDao extends GenericDao<Keyword>{
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

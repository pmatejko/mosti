package dao;

import model.Keyword;
import model.News;

import java.util.Optional;

public interface KeywordDao {
    public Optional<Keyword> create(String name);

    public Optional<Keyword> findKeywordByName(String name);

    public Iterable<News> getNews(Keyword keyword);
}

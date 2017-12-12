package dao;

import model.Article;
import model.News;

import java.util.Optional;

public class ArticleDao extends GenericDao implements INewsDao{
    public Optional<Article> create() {//parametry potem
        return null;
    }

    @Override
    public boolean isNew(News news) {
        return false;
    }
}

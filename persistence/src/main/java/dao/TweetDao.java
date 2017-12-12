package dao;

import model.News;
import model.Tag;
import model.Tweet;

import java.util.Optional;

public class TweetDao extends GenericDao<Tweet> implements INewsDao{
    public Optional<Tweet> create() {//parametry potem
        return null;
    }

    @Override
    public boolean isNew(News news) {
        return false;
    }
}

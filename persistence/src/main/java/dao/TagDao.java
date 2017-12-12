package dao;

import model.Tag;
import model.Tweet;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

public class TagDao extends GenericDao<Tag> {
    public Optional<Tag> create(String name) {
        return null;
    }

    public Optional<Tag> findTagByName(String name){
        return null;
    }

    public Iterable<Tweet> getTweets(Tag tag){
        return null;
    }


}

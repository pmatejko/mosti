import interfaces.IComparator;
import model.Tweet;

public class TweetComparator implements IComparator<Tweet> {
    @Override
    public boolean compareIfNew(Tweet tweet) {
        return false;
    }
}

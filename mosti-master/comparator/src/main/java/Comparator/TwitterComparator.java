package Comparator;


import com.google.inject.Inject;
import model.News;

public class TwitterComparator extends AbstractComparator{

    public TwitterComparator(News news) {
        super(news);
    }

    @Override
    public void process() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}

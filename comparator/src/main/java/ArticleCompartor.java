import interfaces.IComparator;
import model.Article;

public class ArticleCompartor implements IComparator<Article> {

    public ArticleCompartor() {
    }

    @Override
    public boolean compareIfNew(Article article) {
        return false;
    }
}

import Comparator.ComparatorFactory;
import exceptions.DataProviderCOnflictException;
import model.DataProvider;
import model.Preferences;
import org.junit.Test;
import model.News;




public class ComparatorFactoryTest {

    private News news =new News();
    private Preferences preferences = new Preferences();
    private Preferences preferences1 = new Preferences();
    private ComparatorFactory comparatorFactory= new ComparatorFactory();



    @Test(expected= DataProviderCOnflictException.class)
    public void readAndValidate_DataProviderConflict() throws DataProviderCOnflictException {
        preferences.setDataProvider(DataProvider.NEWS_API);
        preferences.setNewsSource("BBC");
        preferences1.setDataProvider(DataProvider.TWITTER_API);
        preferences1.setNewsSource("BBC");
        news.addPreference(preferences);
        news.addPreference(preferences1);

        comparatorFactory.createComparator(news);


    }
}

package fetcher;

import dto.SubscriptionDTO;
import model.News;

import java.util.List;

public enum DataProvider {
    TWITTER_API(1000 * 60 * 10) {
        @Override
        public List<News> fetch(SubscriptionDTO subscription) {
            return null;
        }
    },

    NEWS_API(1000 * 60 * 15) {
        @Override
        public List<News> fetch(SubscriptionDTO subscription) {
            return null;
        }
    };

    /******************************************************************************************
     ******************************************************************************************
     ******************************************************************************************/

    private final long millisecondInterval;


    DataProvider(long millisecondInterval) {
        this.millisecondInterval = millisecondInterval;
    }


    public long getMillisecondInterval() {
        return millisecondInterval;
    }

    public abstract List<News> fetch(SubscriptionDTO subscription);
}

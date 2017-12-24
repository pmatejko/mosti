package model;

public enum DataProvider {
    TWITTER_API(1000 * 60 * 10),
    NEWS_API(1000 * 60 * 15);

    /*******************************************************/

    private final long millisecondInterval;

    DataProvider(long millisecondInterval) {
        this.millisecondInterval = millisecondInterval;
    }

    public long getMillisecondInterval() {
        return millisecondInterval;
    }
}

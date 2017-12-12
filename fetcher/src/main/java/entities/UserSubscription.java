package entities;

import java.util.List;

public class UserSubscription {
    private long userId;
    private long interval;
    private List<NewsSiteSubscription> newsSiteSubscriptions;
    private List<TwitterSubscription> twitterSubscriptions;


    public UserSubscription(List<NewsSiteSubscription> newsSiteSubscriptions,
                            List<TwitterSubscription> twitterSubscriptions) {
        this.newsSiteSubscriptions = newsSiteSubscriptions;
        this.twitterSubscriptions = twitterSubscriptions;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public List<NewsSiteSubscription> getNewsSiteSubscriptions() {
        return newsSiteSubscriptions;
    }

    public void setNewsSiteSubscriptions(List<NewsSiteSubscription> newsSiteSubscriptions) {
        this.newsSiteSubscriptions = newsSiteSubscriptions;
    }

    public List<TwitterSubscription> getTwitterSubscriptions() {
        return twitterSubscriptions;
    }

    public void setTwitterSubscriptions(List<TwitterSubscription> twitterSubscriptions) {
        this.twitterSubscriptions = twitterSubscriptions;
    }

}

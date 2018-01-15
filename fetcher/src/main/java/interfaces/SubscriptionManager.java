package interfaces;

import model.Preferences;

public interface SubscriptionManager {
    void addSubscription(Preferences preferences);
    void cancelSubscription(Preferences preferences);
}

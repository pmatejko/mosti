import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import interfaces.SubscriptionManager;
import model.Preferences;
import notifier.informer.NotifierManager;

import java.io.IOException;

public class MostiApp {
//    private SubscriptionManager subscriptionManager;
    private NotifierManager notifierManager;
    private ComparatorManager comparatorManager;
    private GUI gui;

    @Inject
    MostiApp( NotifierManager notifierManager, ComparatorManager comparatorManager,GUI gui) {
        this.comparatorManager = comparatorManager;
        this.notifierManager = notifierManager;
        this.gui=gui;
//        this.subscriptionManager = subscriptionManager;
    }


//    public void addSubscription(Preferences p) {
//        subscriptionManager.addSubscription(p);
//    }

    public void run() throws IOException {
        comparatorManager.subscribe();
        notifierManager.subscribe();
        gui.start();


    }

}

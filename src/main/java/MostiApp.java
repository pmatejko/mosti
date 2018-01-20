import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import interfaces.SubscriptionManager;
import model.Preferences;
import notifier.informer.NotifierManager;

import java.io.IOException;
import java.util.Scanner;

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
        System.out.println("Press:\n" +
                "1 if you are a new user or if you want to modify your preferences/conditions\n" +
                "any number if you want to run program ");
        Scanner read = new Scanner(System.in);
        int choice = read.nextInt();
        if(choice==1)
            gui.start();
        comparatorManager.subscribe();
        notifierManager.subscribe();
    }





}

package fetcher;

import entities.UserNews;
import entities.UserSubscription;
import io.reactivex.Observer;

import java.util.TimerTask;

public class FetcherTask extends TimerTask {
    private Observer<UserNews> userNewsObserver;
    private UserSubscription subscription;


    public FetcherTask(Observer<UserNews> userNewsObserver,
                       UserSubscription subscription) {
        this.userNewsObserver = userNewsObserver;
        this.subscription = subscription;
    }


    @Override
    public void run() {



        //userNewsObserver.onNext(UserNews);
    }

}

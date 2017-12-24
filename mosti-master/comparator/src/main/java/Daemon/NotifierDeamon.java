package Daemon;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import model.UserNewsDTO;

import java.util.Timer;

@Singleton
public class NotifierDeamon {
    @Inject
    private PublishSubject<UserNewsDTO> userNews;

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return userNews;
    }

    private Timer timer = new Timer();
    private long delay;
    public void run(){

        NotifyTask notifyTask= new NotifyTask();
        timer.schedule(notifyTask,delay);

    }
}

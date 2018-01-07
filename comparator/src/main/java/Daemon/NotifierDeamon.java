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
    private NotifyTask notifyTask;

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return notifyTask.getUserNewsObservable();
    }

    private Timer timer = new Timer();
    private final long delay = 31000;

    public void run() {
        timer.scheduleAtFixedRate(notifyTask, 1000, delay);
    }
}

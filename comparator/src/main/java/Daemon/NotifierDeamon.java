package Daemon;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import model.UserNewsDTO;

import java.util.Timer;

@Singleton
public class NotifierDeamon {
    private final long delay = 31000;
    private NotifyTask notifyTask;
    private Timer timer = new Timer();

    @Inject
    public NotifierDeamon(NotifyTask notifyTask, Timer timer) {
        this.notifyTask = notifyTask;
        this.timer = timer;
    }

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return notifyTask.getUserNewsObservable();
    }

    public void run() {
        timer.scheduleAtFixedRate(notifyTask, 1000, delay);
    }
}

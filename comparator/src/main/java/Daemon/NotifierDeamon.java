package Daemon;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import interfaces.IProvider;
import io.reactivex.Observable;
import model.UserNewsDTO;

import java.util.Timer;

@Singleton
public class NotifierDeamon implements IProvider{
    private final long delay = 60 * 1000;
    private NotifyTask notifyTask;
    private Timer timer = new Timer();

    @Inject
    public NotifierDeamon(NotifyTask notifyTask, Timer timer) {
        this.notifyTask = notifyTask;
        this.timer = timer;
        run();
    }

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return notifyTask.getUserNewsObservable();
    }

    public void run() {
        timer.scheduleAtFixedRate(notifyTask, 1000, delay);
    }
}

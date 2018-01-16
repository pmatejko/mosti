package Daemon;

import com.google.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import model.UserNewsDTO;

import java.util.TimerTask;


public class NotifyTask extends TimerTask {

    @Inject
    private PublishSubject<UserNewsDTO> userNews;
    @Inject
    private Processor processor;
    @Inject
    private TimeManager timeManager;

    @Override
    public void run() {
        if (timeManager.isTimeToSend())
            processor.fetchUserNewsDTO()
                    .forEach(userNews::onNext);

    }

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return userNews;
    }
}

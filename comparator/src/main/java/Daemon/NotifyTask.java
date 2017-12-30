package Daemon;

import com.google.inject.Inject;
import dao.UserDao;
import io.reactivex.subjects.PublishSubject;
import model.UserNewsDTO;

import java.util.TimerTask;


public class NotifyTask extends TimerTask {
    @Inject
    private UserDao userDao;
    @Inject
    private PublishSubject<UserNewsDTO> userNews;
    private TimeManager timeManager = new TimeManager();

    @Override
    public void run() {
        if(timeManager.isAllowedToSend())
            userDao.getUsersToNotify().stream()
            .map(user -> new UserNewsDTO(user,userDao.getNewsToSend(user)))
            .forEach(userNews::onNext);
    }
}

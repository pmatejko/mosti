package Daemon;

import com.google.inject.Inject;
import dao.NewsDao;
import dao.UserDao;
<<<<<<< HEAD
=======
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import model.News;
import model.User;
>>>>>>> f6bf9f707452e51f0e1ecca10dce161bdc6de80e
import model.UserNewsDTO;


import java.util.TimerTask;


public class NotifyTask extends TimerTask {
    @Inject
    private UserDao userDao;
    @Inject
    private PublishSubject<UserNewsDTO> userNews;

    @Override
    public void run() {
        userDao.getUsersToNotify().stream()
        .map(user -> new UserNewsDTO(user,userDao.getNewsToSend(user)))
        .forEach(userNews::onNext);
    }
}

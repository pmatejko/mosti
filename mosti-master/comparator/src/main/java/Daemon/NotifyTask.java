package Daemon;

import com.google.inject.Inject;
import dao.NewsDao;
import dao.UserDao;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import model.News;
import model.User;
import model.UserNewsDTO;
import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.List;
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

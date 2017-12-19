package Daemon;

import com.google.inject.Inject;
import dao.NewsDao;
import dao.UserDao;
import model.News;
import model.User;
import model.UserNewsDTO;
import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.List;
import java.util.TimerTask;


public class NotifyTask extends TimerTask {
    @Inject
    UserDao userDao;
    @Inject
    NewsDao newsDao;
//    @Inject
//    Informer informer


    @Override
    public void run() {
        userDao.getUsersToNotify().stream()
        .map(user -> new UserNewsDTO(user,userDao.getNewsToSend(user)));
//                .forEach(informer::informUser);
    }
}

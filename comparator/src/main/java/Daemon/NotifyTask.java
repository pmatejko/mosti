package Daemon;

import com.google.inject.Inject;
import dao.UserDao;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import model.User;
import model.UserNewsDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;


public class NotifyTask extends TimerTask {
    @Inject
    private UserDao userDao;
    @Inject
    private PublishSubject<UserNewsDTO> userNews;
    private TimeManager timeManager = new TimeManager();

    @Override
    public void run() {
        List<User> u = new LinkedList<>();
        if(timeManager.isAllowedToSend()){
            u =userDao.getUsersToNotify();
            System.out.println("dlugosc listy "+u.size());
        u.stream()
            .map(user -> new UserNewsDTO(user,userDao.getNewsToSend(user))) // add filter?
            .forEach(dto ->{
                if(dto.getNewsList().isEmpty())
                    System.out.println("empty newslist");
                else
                    userNews.onNext(dto);
                System.out.println(dto.getUser().getLastNotification());
                // TODO refactor, now it's for debug and its's ugly
            });
        }
    }
    public Observable<UserNewsDTO> getUserNewsObservable() {
        return userNews;
    }
}

package Daemon;

import com.google.inject.Inject;
import dao.UserDao;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import io.reactivex.Observer;
=======
import io.reactivex.Observable;
>>>>>>> d43e0d853fd35d9947ee0fa4ef94b8c1730fbd09
import io.reactivex.subjects.PublishSubject;
import model.User;
>>>>>>> f6bf9f707452e51f0e1ecca10dce161bdc6de80e
import model.UserNewsDTO;

<<<<<<< HEAD

=======
import java.util.LinkedList;
import java.util.List;
>>>>>>> d43e0d853fd35d9947ee0fa4ef94b8c1730fbd09
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

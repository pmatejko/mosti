package Daemon;

import Comparator.ComparatorFactory;
import com.google.inject.Inject;
import dao.UserDao;
import interfaces.IComparator;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import model.News;
import model.User;
import model.UserNewsDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;


public class NotifyTask extends TimerTask {
    @Inject
    private UserDao userDao;
    @Inject
    private PublishSubject<UserNewsDTO> userNews;
    @Inject
    private ComparatorFactory comparatorFactory;
    private TimeManager timeManager = new TimeManager();

    @Override
    public void run() {
        if(timeManager.isAllowedToSend()){
            userDao.getUsersToNotify().stream()
            .map(user -> new UserNewsDTO(user,getNewsToSend(user))) // add filter?
            .forEach(dto ->{
                if(dto.getNewsList().isEmpty())
                    System.out.println("empty newslist"); //logging needs to be added
                else
                    userNews.onNext(dto);
                // TODO refactor, now it's for debug and its's ugly
            });
        }
    }

    private List<News> getNewsToSend(User user) {
        IComparator comparator = comparatorFactory.createComparator(user);
        return userDao.getNewsToSend(user).stream()
                .filter(comparator::process)
                .collect(Collectors.toList());
    }

    public Observable<UserNewsDTO> getUserNewsObservable() {
        return userNews;
    }
}

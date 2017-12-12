package interfaces;

import entities.UserNews;
import io.reactivex.Observable;

public interface INotifierObservable {
    Observable<UserNews> getUserNews();
}

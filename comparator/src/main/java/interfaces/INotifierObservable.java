package interfaces;

import entities.UserNews;
import io.reactivex.Observable;

public interface INotifierObservable<T> {
    Observable<T> getUserNews();
}

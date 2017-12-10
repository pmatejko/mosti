package interfaces;

import entities.UserNews;
import io.reactivex.Observable;

public interface IFetcher {
    Observable<UserNews> getUserNewsObservable();
}

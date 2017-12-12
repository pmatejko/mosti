import entities.UserNews;
import interfaces.INotifierObservable;
import io.reactivex.Observable;

public class NotifierSupplier implements INotifierObservable {
    @Override
    public Observable<UserNews> getUserNews() {
        return null;
    }
}

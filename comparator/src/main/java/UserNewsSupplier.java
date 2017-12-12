import entities.UserNews;
import interfaces.INotifierObservable;
import io.reactivex.Observable;

public class UserNewsSupplier implements INotifierObservable<UserNews> {
    private final static UserNewsSupplier INSTANCE = new UserNewsSupplier();

    private Observable<UserNews> supplier;

    public static UserNewsSupplier getINSTANCE() {
        return INSTANCE;
    }
    public UserNewsSupplier(){
        supplier = Observable.empty();
    }

    @Override
    public Observable<UserNews> getUserNews() {
        return supplier;
    }
}

import com.google.inject.Singleton;
import model.UserNewsDTO;
import interfaces.INotifierObservable;
import io.reactivex.Observable;

@Singleton
public class UserNewsSupplier implements INotifierObservable<UserNewsDTO> {

    private Observable<UserNewsDTO> supplier;



    @Override
    public Observable<UserNewsDTO> getUserNews() {
        return supplier;
    }
}

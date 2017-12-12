import entities.UserNews;
import io.reactivex.Observable;


public class FetchingHandler {
    private final static FetchingHandler INSTANCE = new FetchingHandler();
    Observable<UserNews> userNewsObservable; // will be injected as singleton form Fetcher module

    public static FetchingHandler getINSTANCE() {
        return INSTANCE;
    }

    public void subscribe(){

    }
}

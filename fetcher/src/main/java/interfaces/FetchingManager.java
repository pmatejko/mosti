package interfaces;

import io.reactivex.Observable;

public interface FetchingManager<T> {
    Observable<T> getObservable();
}

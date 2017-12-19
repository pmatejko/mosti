package interfaces;

import dto.NewsDTO;
import io.reactivex.Observable;

public interface IFetchingManager {
    Observable<NewsDTO> getNewsObservable();
}

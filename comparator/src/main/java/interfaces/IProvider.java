package interfaces;


import io.reactivex.Observable;
import model.UserNewsDTO;

public interface IProvider {
    Observable<UserNewsDTO> getUserNewsObservable();
}

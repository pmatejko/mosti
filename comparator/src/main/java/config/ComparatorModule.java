package config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dao.CompareTypeDao;
import dao.NewsDao;
import dao.PreferencesDao;
import dao.UserDao;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.NewsDaoImpl;
import daoImpl.PreferencesDaoImpl;
import daoImpl.UserDaoImpl;
import dto.NewsDTO;
import fetcher.FetchingManager;
import interfaces.IFetchingManager;
import io.reactivex.Observable;


public class ComparatorModule extends AbstractModule {
    @Override
    protected void configure() {

        TypeLiteral<Observable<NewsDTO>> observableTypeLiteral = new TypeLiteral<Observable<NewsDTO>>() {};

        bind(NewsDao.class).to(NewsDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(PreferencesDao.class).to(PreferencesDaoImpl.class);
        bind(CompareTypeDao.class).to(CompareTypeDaoImpl.class);
        bind(IFetchingManager.class).to(FetchingManager.class);
        bind(Observable.class).to(observableTypeLiteral);

    }
}

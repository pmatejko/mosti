import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import dao.CompareTypeDao;
import dao.NewsDao;
import dao.PreferencesDao;
import dao.UserDao;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.NewsDaoImpl;
import daoImpl.PreferencesDaoImpl;
import daoImpl.UserDaoImpl;
import dto.NewsDTO;
import fetcher.FetcherTask;
import fetcher.FetchingManager;
import guice.GuiceFetcherProvider;
import interfaces.FetcherProvider;
import interfaces.FetcherTaskFactory;
import interfaces.IFetchingManager;
import interfaces.PropertiesManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import util.DefaultPropertiesManager;

public class Config extends AbstractModule {

    @Override
    protected void configure() {
        TypeLiteral<PublishSubject<NewsDTO>> publishSubjectTypeLiteral = new TypeLiteral<PublishSubject<NewsDTO>>() {
        };
        TypeLiteral<Observable<NewsDTO>> observableTypeLiteral = new TypeLiteral<Observable<NewsDTO>>() {
        };
        TypeLiteral<Observer<NewsDTO>> observerTypeLiteral = new TypeLiteral<Observer<NewsDTO>>() {
        };

        bind(publishSubjectTypeLiteral)
                .in(Singleton.class);
        bind(observableTypeLiteral)
                .to(publishSubjectTypeLiteral);
        bind(observerTypeLiteral)
                .to(publishSubjectTypeLiteral);

        bind(FetcherProvider.class)
                .to(GuiceFetcherProvider.class);

        bind(PropertiesManager.class)
                .to(DefaultPropertiesManager.class);

        install(new FactoryModuleBuilder()
                .implement(FetcherTask.class, FetcherTask.class)
                .build(FetcherTaskFactory.class));
        bind(IFetchingManager.class).to(FetchingManager.class);
        bind(NewsDao.class).to(NewsDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(PreferencesDao.class).to(PreferencesDaoImpl.class);
        bind(CompareTypeDao.class).to(CompareTypeDaoImpl.class);
    }
}

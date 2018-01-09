import Comparator.LengthComparator;
import Comparator.VocabularyComparator;
import com.google.inject.*;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import dao.CompareTypeDao;
import dao.NewsDao;
import dao.PreferencesDao;
import dao.UserDao;
import daoImpl.CompareTypeDaoImpl;
import daoImpl.NewsDaoImpl;
import daoImpl.PreferencesDaoImpl;
import daoImpl.UserDaoImpl;
import dto.NewsDTO;
import fetcher.FetcherRunnable;
import fetcher.FetchingManager;
import guice.GuiceFetcherProvider;
import interfaces.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import util.DefaultPropertiesManager;
import util.RetryingRunnable;
import util.RetryingScheduledExecutor;

import java.util.concurrent.ScheduledExecutorService;

public class Config extends AbstractModule {

    @Override
    protected void configure() {
        TypeLiteral<PublishSubject<NewsDTO>> publishSubjectTypeLiteral = new TypeLiteral<PublishSubject<NewsDTO>>() {};
        TypeLiteral<Observable<NewsDTO>> observableTypeLiteral = new TypeLiteral<Observable<NewsDTO>>() {};
        TypeLiteral<Observer<NewsDTO>> observerTypeLiteral = new TypeLiteral<Observer<NewsDTO>>() {};

        bind(publishSubjectTypeLiteral)
                .in(Singleton.class);
        bind(observableTypeLiteral)
                .to(publishSubjectTypeLiteral);
        bind(observerTypeLiteral)
                .to(publishSubjectTypeLiteral);

        bindConstant()
                .annotatedWith(Names.named(RetryingScheduledExecutor.CORE_POOL_SIZE))
                .to(10);

        bind(FetcherProvider.class)
                .to(GuiceFetcherProvider.class);

        bind(PropertiesManager.class)
                .to(DefaultPropertiesManager.class);

        bind(RetryingExecutor.class)
                .to(RetryingScheduledExecutor.class);

        bind(ScheduledExecutorService.class)
                .to(RetryingScheduledExecutor.class);

        install(new FactoryModuleBuilder()
                .implement(FetcherRunnable.class, FetcherRunnable.class)
                .build(FetcherRunnableFactory.class));

        install(new FactoryModuleBuilder()
                .implement(RetryingRunnable.class, RetryingRunnable.class)
                .build(RetryingRunnableFactory.class));


        bind(IFetchingManager.class).to(FetchingManager.class);
        bind(NewsDao.class).to(NewsDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(PreferencesDao.class).to(PreferencesDaoImpl.class);
        bind(CompareTypeDao.class).to(CompareTypeDaoImpl.class);

//
        Multibinder<IConfigurableComparator> compBinder = Multibinder.newSetBinder(binder(), IConfigurableComparator.class);
        compBinder.addBinding().to(LengthComparator.class);
        compBinder.addBinding().to(VocabularyComparator.class);



    }
//    @Provides
//    @Singleton
//    public static ComparatorFactory getSomeClass(Injector injector) {
//        Set<IConfigurableComparator> allYourInterfaces = new HashSet<>();
//        for (Key<?> key : injector.getAllBindings().keySet()) {
//
//            if (IConfigurableComparator.class.isAssignableFrom(key.getTypeLiteral().getRawType())) {
//                IConfigurableComparator yourInterface = (IConfigurableComparator) injector.getInstance(key);
//                allYourInterfaces.add(yourInterface);
//            }
//
//        }
//        return new ComparatorFactory(allYourInterfaces);
//    }
}

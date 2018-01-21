package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import dao.PreferencesDao;
import daoImpl.PreferencesDaoImpl;
import dto.NewsDTO;
import fetcher.FetcherRunnable;
import fetcher.FetchingManager;
import fetcher.impl.NewsAPIFetcher;
import fetcher.impl.TwitterAPIFetcher;
import fetcher.impl.connectors.TwitterAPIConnector;
import interfaces.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import util.CorePoolSize;
import util.DefaultPropertiesManager;
import util.RetryingRunnable;
import util.RetryingScheduledExecutor;

import java.util.concurrent.ScheduledExecutorService;

public class FetcherModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PersistanceModule());

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
                .annotatedWith(CorePoolSize.class)
                .to(10);

        bind(SubscriptionManager.class)
                .to(FetchingManager.class);
        bind(IFetchingManager.class)
                .to(FetchingManager.class);

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

        Multibinder<Fetcher> fetcherBinder = Multibinder.newSetBinder(binder(), Fetcher.class);
        fetcherBinder.addBinding().to(NewsAPIFetcher.class);
        fetcherBinder.addBinding().to(TwitterAPIFetcher.class);
    }
}

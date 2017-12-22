package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import dto.NewsDTO;
import fetcher.FetcherTask;
import interfaces.FetcherTaskFactory;
import interfaces.FetcherProvider;
import interfaces.PropertiesManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import util.DefaultPropertiesManager;

public class FetcherModule extends AbstractModule {

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

        bind(FetcherProvider.class)
                .to(GuiceFetcherProvider.class);

        bind(PropertiesManager.class)
                .to(DefaultPropertiesManager.class);

        install(new FactoryModuleBuilder()
                .implement(FetcherTask.class, FetcherTask.class)
                .build(FetcherTaskFactory.class));
    }
}

package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import fetcher.FetcherFactory;
import interfaces.IFetcherFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

public class FetcherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PublishSubject.class)
                .in(Singleton.class);
        bind(Observable.class)
                .to(PublishSubject.class);
        bind(Observer.class)
                .to(PublishSubject.class);

        bind(IFetcherFactory.class)
                .to(FetcherFactory.class);
    }
}
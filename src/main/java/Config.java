import com.google.inject.AbstractModule;

import guice.ComparatorModule;
import guice.FetcherModule;
import guice.PersistanceModule;
import notifier.guice.NotifierModule;

public class Config extends AbstractModule {

    @Override
    protected void configure() {
        install(new PersistanceModule());
        install(new FetcherModule());
        install(new ComparatorModule());
        install(new NotifierModule());

    }

}

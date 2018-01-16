package guice;

import Comparator.LengthComparator;
import Comparator.VocabularyComparator;
import Daemon.NotifierDeamon;
import com.google.inject.*;
import com.google.inject.multibindings.Multibinder;

import interfaces.*;


public class ComparatorModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new FetcherModule());
        install(new PersistanceModule());

        bind(IProvider.class).to(NotifierDeamon.class);
        Multibinder<IConfigurableComparator> compBinder = Multibinder.newSetBinder(binder(), IConfigurableComparator.class);
        compBinder.addBinding().to(LengthComparator.class);
        compBinder.addBinding().to(VocabularyComparator.class);

    }
}

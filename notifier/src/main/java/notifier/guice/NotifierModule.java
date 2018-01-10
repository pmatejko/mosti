package notifier.guice;

import guice.ComparatorModule;

import com.google.inject.*;


public class NotifierModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new ComparatorModule());

    }
}
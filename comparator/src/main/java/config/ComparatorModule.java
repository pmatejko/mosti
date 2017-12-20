package config;

import com.google.inject.AbstractModule;
import dao.NewsDao;
import daoImpl.NewsDaoImpl;

public class ComparatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NewsDao.class).to(NewsDaoImpl.class);
    }
}

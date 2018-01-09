package guice;

import com.google.inject.AbstractModule;
import dao.CompareTypeDao;
import dao.NewsDao;
import dao.PreferencesDao;
import dao.UserDao;
import daoImpl.ConditionDaoImpl;
import daoImpl.NewsDaoImpl;
import daoImpl.PreferencesDaoImpl;
import daoImpl.UserDaoImpl;

public class PersistanceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(NewsDao.class).to(NewsDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(PreferencesDao.class).to(PreferencesDaoImpl.class);
        bind(CompareTypeDao.class).to(ConditionDaoImpl.class);
    }
}

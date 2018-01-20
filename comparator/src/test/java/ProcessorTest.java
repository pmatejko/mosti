import Comparator.ComparatorFactory;

import Daemon.Processor;
import dao.UserDao;
import interfaces.IComparator;
import model.News;
import model.User;
import model.UserNewsDTO;
import org.junit.Test;
import Comparator.ComparatorComposite;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessorTest {
    @Test
    public void RigthAmountOfWordsTest() {
        UserDao userDao = mock(UserDao.class);
        ComparatorFactory comparatorFactory = mock(ComparatorFactory.class);
        Processor processor = new Processor(userDao, comparatorFactory);
        User user = new User();
        user.setId(1);

        List<User> userList = new LinkedList<>();
        userList.add(user);

        News news = new News();
        news.setId(1);


        List<News> newsList = new LinkedList<>();
        newsList.add(news);

        ComparatorFactory comparatorFactory1 = mock(ComparatorFactory.class);
        IComparator comparator = mock(ComparatorComposite.class);
        List<UserNewsDTO> userNewsDTO = new LinkedList<>();
        UserNewsDTO userNewsDTO1 = new UserNewsDTO(user,newsList);
        userNewsDTO.add(userNewsDTO1);

        when(userDao.getUsersToNotify()).thenReturn(userList);
        when(userDao.getNewsToSend(user)).thenReturn(newsList);
        when(comparatorFactory1.createComparatorForUser(user)).thenReturn(comparator);
        when(comparator.process(news)).thenReturn(true);

        assertEquals(userNewsDTO, processor.fetchUserNewsDTO());

    }
}

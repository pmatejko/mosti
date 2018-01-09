package Daemon;


import Comparator.ComparatorFactory;
import com.google.inject.Inject;
import dao.UserDao;
import interfaces.IComparator;
import model.News;
import model.User;
import model.UserNewsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Processor {

    private UserDao userDao;
    private ComparatorFactory comparatorFactory;

    @Inject
    public Processor(UserDao userDao, ComparatorFactory comparatorFactory) {
        this.userDao = userDao;
        this.comparatorFactory = comparatorFactory;
    }

    public List<UserNewsDTO> fetchUserNewsDTO() {
        return userDao
                .getUsersToNotify()
                .stream()
                .map(this::wrapInDTO)
                .filter(UserNewsDTO::isNotEmpty)
                .collect(Collectors.toList());
    }

    private UserNewsDTO wrapInDTO(User user) {
        return new UserNewsDTO(user, getNewsToSend(user));
    }

    private List<News> getNewsToSend(User user) {
        IComparator comparator = comparatorFactory.createComparatorForUser(user);
        return userDao.getNewsToSend(user).stream()
                .filter(comparator::process)
                .collect(Collectors.toList());
    }
}

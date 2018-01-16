import com.google.inject.Inject;
import dao.ConditionDao;
import dao.PreferencesDao;
import dao.UserDao;
import daoImpl.ConditionDaoImpl;
import daoImpl.PreferencesDaoImpl;
import daoImpl.UserDaoImpl;
import interfaces.SubscriptionManager;
import model.*;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;

public class GUI {
    private SubscriptionManager subscriptionManager;
    private UserDao userDao;
    private PreferencesDao preferencesDao;
    private ConditionDao conditionDao;

    @Inject
    GUI(SubscriptionManager subscriptionManager,
        PreferencesDao preferencesDao,
        UserDao userDao,
        ConditionDao conditionDao) {
        this.subscriptionManager = subscriptionManager;
        this.preferencesDao=preferencesDao;
        this.userDao=userDao;
        this.conditionDao =conditionDao;
    }


    public void addSubscription(Preferences p) {
        subscriptionManager.addSubscription(p);
    }





    public  void start() throws IOException {
        Scanner read = new Scanner(System.in);
        while (true) {

            User user = createUser();
            selectConditions(user);
            selectPreferences(user);




        }

    }

    private  User createUser() {
        Scanner read = new Scanner(System.in);

            System.out.println("If you are a new user press 1  ");
            int ifNewUser = read.nextInt();
            if (ifNewUser == 1) {
                User user = new User();
                System.out.println("Please write your email to be informed about news");
                user.setEmail(read.next());
                System.out.println("How often do you want to be notified? Choose time in minutes");
                user.setInterval(read.nextInt());
                userDao.save(user);
                return user;

//        } else if (ifNewUser == 2) {
//            System.out.println("Write your email to change your previous choices");
//            String email=read.next();
//            TODO ?
            } else {
                System.out.println("You've choosen wrong parameter. You can only press 1 ");
                return createUser();
            }


    }

    private void selectConditions(User user) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("You can choose conditions like length and vocabulary. If you want to choose one of them press 1." +
                "If you want to choose both press 2. If you don't want any of them press 0");
        int amountOfConditions = Integer.valueOf(read.readLine());
        if (amountOfConditions == 1) {
            Condition condition = new Condition();
            System.out.println("If you want length condition- press 1, vocabulary condition- press 2.  after space write value");
            String[] parameters = read.readLine().split(" ");
            if (Integer.parseInt(parameters[0]) == 1) {
                condition.setType(ConditionType.LENGTH);
                condition.setValue(Integer.parseInt(parameters[1]));
                condition.setUser(user);
                user.addCondition(condition);
                userDao.update(user);
            } else if (Integer.parseInt(parameters[0]) == 2) {
                condition.setType(ConditionType.VOCABULARY);
                condition.setValue(Integer.parseInt(parameters[1]));
                condition.setUser(user);
                user.addCondition(condition);
                userDao.update(user);
            } else {
                System.out.println("You chose wrong argument. try again");
                selectConditions(user);
            }


        } else if (amountOfConditions == 2) {
            Condition condition = new Condition();
            Condition condition1 = new Condition();
            System.out.println("How long should be your news and how many different words should it contain ");
            String[] parameters = read.readLine().split(" ");
            condition.setType(ConditionType.LENGTH);
            condition.setValue(Integer.parseInt(parameters[0]));
            condition1.setType(ConditionType.VOCABULARY);
            condition1.setValue(Integer.parseInt(parameters[1]));
            condition.setUser(user);
            condition1.setUser(user);
            user.addCondition(condition);
            user.addCondition(condition1);


        } else if (amountOfConditions == 0) {
            System.out.println("you chose default condition");
        } else {
            System.out.println("You can only press 1, 2 or 0, please try again");
            selectConditions(user);
        }

    }

    private void selectPreferences(User user) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int cond =0;
        do {
            System.out.println("Write keyword, news Source and Data Provider. Available data providers: TWITTER_API and NEWS_API");
            String[]parameters=read.readLine().split(" ");
            Optional<Preferences> p;
            if(validate(parameters,parameters[2])) {
                p = preferencesDao.findPreferences(parameters[0], parameters[1], toDataProvider(parameters[2]));
                Preferences preferences= p.orElse(createAndSubscribe(parameters[0], parameters[1], parameters[2]));
                user.addPreferences(preferences);
                preferences.addUser(user);
                addSubscription(preferences);
                userDao.update(user);
                System.out.println("saved");
            }
            else
                selectPreferences(user);






            System.out.println("If you want to add another preferences press 1, else press anything");
           cond = Integer.valueOf(read.readLine());
        }while(cond==1);
    }

    private Preferences createAndSubscribe(String keyword, String newSource, String provider){
       Preferences p= new Preferences(keyword,newSource,toDataProvider(provider));
       addSubscription(p);
       return p;

    }
    private DataProvider toDataProvider(String provider){

        for(DataProvider p: DataProvider.values())
            if( provider.equalsIgnoreCase(p.name()) )
                return p;

        throw  new IllegalArgumentException();
    }

    private Boolean validate(String[] parameters, String provider){
        for(DataProvider p: DataProvider.values())
          if( p.name().toLowerCase().startsWith(provider.toLowerCase()) && parameters.length==3)
            return true;
       return false;
    }
}
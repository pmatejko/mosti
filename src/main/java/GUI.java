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

import javax.jws.soap.SOAPBinding;
import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Executor;

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
        this.preferencesDao = preferencesDao;
        this.userDao = userDao;
        this.conditionDao = conditionDao;
    }


    public void addSubscription(Preferences p) {
        subscriptionManager.addSubscription(p);
    }


    public void start() throws IOException {

        try {
            Scanner read = new Scanner(System.in);
            System.out.println("If you are a new user press 1, if you want to change your preferences/conditions press 2 ");
            int ifNewUser = read.nextInt();
            if (ifNewUser == 1) {
                User user = createUser();
                selectConditions(user);
                selectPreferences(user);

            } else if (ifNewUser == 2) {
                User user = getUser();
                changeCondition(user);
                changePreferences(user);
                userDao.update(user);
            } else {
                System.out.println("You've choosen wrong parameter. You can only press 1 or 2");
                start();
            }
            System.out.println("If you still want to change something or add new usser press 1 " +
                    "else press other number");

            ifNewUser = read.nextInt();
            if (ifNewUser == 1)
                start();

            System.out.println("starting application");
        }catch (Exception e){
            System.out.println("You typed something wrong. Try again buddy");
            start();
        }

    }

    private void changePreferences(User user) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        Scanner read1 = new Scanner(System.in);

        List<Preferences> userPreferences = user.getPreferences();
        System.out.println("these are your preferences:  " + userPreferences + "\n" +
                "if you want to add more press 1. If you want to delete some of them press 2 if you do not want to" +
                "change any preferences press 3");

        int choice =read1.nextInt();
        if(choice==1)
            addPreferences(user, userPreferences);
        else if(choice==2) {
            System.out.println("Write ids of preferences you want to delete ");
            String[] preferencesToDelete=read.readLine().split(" ");
            deletePreferences(user,preferencesToDelete );
        }
        else if(choice==3)
            return;
        else {
            System.out.println("You've passed wrong parameters. Please try again");
            changePreferences(user);
        }
        System.out.println("if you want to add or delete anything else please press 1 else press other number");
        choice=read1.nextInt();
        if (choice==1)
            changePreferences(user);


    }


private void deletePreferences(User user,String[] preferencesToDelete) {
    for (String toDelete : preferencesToDelete){
        Long id = Long.parseLong(toDelete);
        preferencesDao.delete(id);
        List<Preferences> userPreferences =user.getPreferences();
        for(int i =0;i<userPreferences.size();i++)
            if (userPreferences.get(i).getId() ==id)
                user.removePreference(i);
    }

}


    private void addPreferences(User user, List<Preferences> userPreferences) throws IOException {
        List<Preferences> newPreferences = new LinkedList<>();
        System.out.println("Type preferences as follows: keyword1 newsSource1 dataProvider1,k2 nS2 dP2 ...");
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String[] newPreferencesAsString = read.readLine().split(",");
        //czy uzywac metody validate skoro w selectpreferences używamy
        //czy przy tworzeniu nowego preferences możemy zorbić addsubsciption skoro nie odpalmy jeszze naszego programu

        for (String pref : newPreferencesAsString) {
            String[] values = pref.split(" ");
             Preferences p = preferencesDao.findPreferences(values[0],values[1], toDataProvider(values[2])).orElse(createAndSubscribe(values[0], values[1],values[2]));
            user.addPreferences(p);
            p.addUser(user);

        }


    }

    private void changeCondition(User user) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        Scanner read1 = new Scanner(System.in);
        List<Condition> userConditions = user.getConditions();
        System.out.println("these are your conditions:  " + userConditions + "\n" +
                "if you want to add more press 1. If you want to delete some of them press 2 if you do not want to" +
                "change any condition press 3");

        System.out.println("Available conditions:"+ Arrays.toString(ConditionType.values()));


       int choice =read1.nextInt();
       if(choice==1)
        addConditions(user, userConditions);
       else if(choice==2) {
           System.out.println("Write ids of conditions you want to delete ");
           String[] conditionsToDelete=read.readLine().split(" ");
           deleteConditions(user,conditionsToDelete );
       }
       else if(choice==3)
           return;
       else {
           System.out.println("You've passed wrong parameters. Please try again");
           changeCondition(user);
       }
        System.out.println("if you want to add or delete anything else please press 1 else pres anything");
       choice=read1.nextInt();
       if (choice==1)
           changeCondition(user);
    }

    private void deleteConditions(User user, String[] conditionsToDelete) {
//        for (String toDelete : conditionsToDelete)
//            for (int i=0;i<user.getConditions().size();i++)
//                if(user.getConditions().get(i).getId()==Long.parseLong(toDelete)){
//                    user.getConditions().remove(i);
//                }

        for (String toDelete : conditionsToDelete){
            Long id = Long.parseLong(toDelete);
            conditionDao.delete(id);
            List<Condition> userPreferences =user.getConditions();
            for(int i =0;i<userPreferences.size();i++)
                if (userPreferences.get(i).getId() ==id)
                    user.removeCondition(i);
        }

    }

    private void addConditions(User user, List<Condition> userConditions) throws IOException {
        System.out.println("Type conditions as follows: cond1Val cond1Name, cond2Val cond2Name, ...");
        List<Condition> newConditions = new LinkedList<>();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String[] newConditionsAsString = read.readLine().split(",");
        for (String cond : newConditionsAsString) {
            String[] values = cond.split(" ");
            Condition newCond = new Condition();
            newCond.setValue(Integer.parseInt(values[0]));
            newCond.setType(toConditionType(values[1]));
            newConditions.add(newCond);
        }
        List<Condition> conditionsToAdd = new LinkedList<>();
        for (Condition userCond : userConditions)
            for (Condition newCond : newConditions)
                if (userCond.getType() == newCond.getType()) {
                    System.out.println("you already have " + userCond.getType() + " condition defined");
                } else {
                    newCond.setUser(user);
                    conditionsToAdd.add(newCond);
                }
        conditionsToAdd.forEach(user::addCondition);
    }

    private User getUser() throws IOException {
        System.out.println("type your email, or type '1' to get back to the main menu");
        BufferedReader read1 = new BufferedReader(new InputStreamReader(System.in));
        String email = read1.readLine();
        if (email.equals("1"))
            start();
        Optional<User> x= userDao.findUser(email);
        if(x.isPresent())
            return x.get();
        return getUser();

    }

    private User createUser() throws IOException {
        Scanner read = new Scanner(System.in);
        BufferedReader read1 = new BufferedReader(new InputStreamReader(System.in));


        User user = new User();
        System.out.println("Please write your email to be informed about news");
        user.setEmail(read.next());
        System.out.println("How often do you want to be notified? Choose time in minutes");
        user.setInterval(read.nextInt());
        userDao.save(user);
        return user;
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
        int cond = 0;
        do {
            System.out.println("Write keyword, news Source and Data Provider. Available data providers: TWITTER_API and NEWS_API");
            String[] parameters = read.readLine().split(" ");
            Optional<Preferences> p;
            if (validate(parameters, parameters[2])) {
                p = preferencesDao.findPreferences(parameters[0], parameters[1], toDataProvider(parameters[2]));
                Preferences preferences = p.orElse(createAndSubscribe(parameters[0], parameters[1], parameters[2]));
                user.addPreferences(preferences);
                preferences.addUser(user);
                userDao.update(user);
                System.out.println("saved");
            } else
                selectPreferences(user);


            System.out.println("If you want to add another preferences press 1, else press anything");
            cond = Integer.valueOf(read.readLine());
        } while (cond == 1);
    }

    private Preferences createAndSubscribe(String keyword, String newSource, String provider) {
        Preferences p = new Preferences(keyword, newSource, toDataProvider(provider));
        addSubscription(p);
        return p;

    }

    private DataProvider toDataProvider(String provider) {

        for (DataProvider p : DataProvider.values())
            if (provider.equalsIgnoreCase(p.name()))
                return p;

        throw new IllegalArgumentException();
    }

    private ConditionType toConditionType(String conditionType) {

        for (ConditionType p : ConditionType.values())
            if (conditionType.equalsIgnoreCase(p.name()))
                return p;

        throw new IllegalArgumentException();
    }


    private Boolean validate(String[] parameters, String provider) {
        for (DataProvider p : DataProvider.values())
            if (p.name().toLowerCase().startsWith(provider.toLowerCase()) && parameters.length == 3)
                return true;
        return false;
    }
}
import Comparator.ComparatorManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.xml.internal.bind.v2.TODO;
import daoImpl.UserDaoImpl;
import interfaces.IProvider;
import interfaces.SubscriptionManager;
import model.*;
import notifier.informer.NotifierManager;
import org.hibernate.Session;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new Config());
        MostiApp mostiApp = injector.getInstance(MostiApp.class);
        mostiApp.run();


}
}

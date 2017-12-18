package Daemon;

import com.google.inject.Singleton;

import java.util.Timer;

@Singleton
public class NotifierDeamon {
    private Timer timer = new Timer();
    private long delay;
    public void run(){
        NotifyTask notifyTask= new NotifyTask();
        timer.schedule(notifyTask,delay);

    }
}

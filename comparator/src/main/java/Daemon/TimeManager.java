package Daemon;

import java.util.Calendar;

public class TimeManager {
    private static final int NIGHT_START = 22;
    private static final int NIGHT_ENDS = 6;

    public boolean isTimeToSend(){
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return currentHour<NIGHT_START && currentHour >=NIGHT_ENDS;
    }
}

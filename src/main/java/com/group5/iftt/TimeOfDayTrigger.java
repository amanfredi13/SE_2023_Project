package com.group5.iftt;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeOfDayTrigger {
private String hour;
private String minute;
    public void timeTrigger(String hour, String minute){
        this.hour=hour;
        this.minute=minute;
    }

public boolean isValidate(){
    //tronca ai minuti
    LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    LocalTime selectedTime = LocalTime.parse(hour+":"+minute);
    if(currentTime.equals(selectedTime)){
        return true;
    }
    return false;
}
}


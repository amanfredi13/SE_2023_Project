package com.group5.iftt;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeOfDayTrigger {
private String hour;
private String minute;

public boolean isValidate(){
    LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    LocalTime userSelectedTime = userSelectedTime.parse(hour+":"+minute);
 if(currentTime.equals(userSelectedTime)){
    return true;
 }
 return false;
}
public void timeTrigger(int hour, int minute){
    this.hour=hour;
    this.minute=minute;
}
}


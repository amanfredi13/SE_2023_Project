package com.group5.iftt;

public class TimeOfDayTrigger {
private int hour;
private int minute;

public boolean isValidate(){
 return true;
}
public void timeTrigger(int hour, int minute){
    this.hour=hour;
    this.minute=minute;
}
}


package com.group5.iftt.Model;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeOfDayTrigger implements Trigger, Serializable {
    private  LocalTime triggerTime;

    public TimeOfDayTrigger(String hour, String minute) {
        this.triggerTime = LocalTime.parse(hour+ ":" + minute); //formatto correttamente l'orario dell'utente oer poterlo confrontare
    }
    @Override
    public String toString() {
        return triggerTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    public TimeOfDayTrigger(){}

    public boolean isValidate() {
       LocalTime currentTime= LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return currentTime.equals(triggerTime); //Confronto tempo inserito dall'utente nel trigger con il tempo attuale
    }
    public int getHour() {
        return triggerTime.getHour();
    }

    public int getMinute() {
        return triggerTime.getMinute();
    }

    public String getTimeofDay(){
        return triggerTime.toString();
    }




}



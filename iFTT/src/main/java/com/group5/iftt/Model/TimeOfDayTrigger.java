package com.group5.iftt.Model;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeOfDayTrigger implements Trigger, Serializable {
    private  LocalTime triggerTime;

    public TimeOfDayTrigger(String hour, String minute) {
        this.triggerTime = LocalTime.parse(hour+ ":" + minute); //formatto correttamente l'orario dell'utente oer poterlo confrontare
    }

    public TimeOfDayTrigger(){}

    public boolean isValidate() {
       LocalTime currentTime= LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return currentTime.equals(triggerTime); //Confronto tempo inserito dall'utente nel trigger con il tempo attuale
    }

    public String getTimeofDay(){
        return triggerTime.toString();
    }
}


package com.group5.iftt.Model;

import com.group5.iftt.Model.Trigger;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeOfDayTrigger implements Trigger {
    private final LocalTime triggerTime;

    public TimeOfDayTrigger(String hour, String minute) {
        this.triggerTime = LocalTime.parse(hour+ ":" + minute); //formatto correttamente l'orario dell'utente oer poterlo confrontare
    }

    public boolean isValidate() {
       LocalTime currentTime= LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return currentTime.equals(triggerTime); //confronto tempo inserito dall'utente nel trigger con il tempo attuale
    }
}


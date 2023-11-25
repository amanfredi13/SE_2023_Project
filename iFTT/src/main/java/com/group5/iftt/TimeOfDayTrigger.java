package com.group5.iftt;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeOfDayTrigger {
    private LocalTime triggerTime;

    public TimeOfDayTrigger(String hour, String minute) {
        this.triggerTime = LocalTime.parse(hour+ ":" + minute);
    }

    public boolean isValidate() {
       LocalTime currentTime= LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        return currentTime.equals(triggerTime);
    }

}


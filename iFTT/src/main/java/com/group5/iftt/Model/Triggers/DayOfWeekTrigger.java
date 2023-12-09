package com.group5.iftt.Model.Triggers;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayOfWeekTrigger implements Trigger, Serializable {
    private String triggerDay;

    public DayOfWeekTrigger(String dayOfWeek) {
        this.triggerDay = dayOfWeek;
    }

    @Override
    public String toString() {
        return "Giorno della settimana";
    }

    public DayOfWeekTrigger() {
    }

    public boolean isValidate() {


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ITALIAN);
        String currentDay = sdf.format(new Date()).toLowerCase();
        return currentDay.equals(triggerDay.toLowerCase());


    }

    public String getTriggerDay() {
        return triggerDay;
    }

}

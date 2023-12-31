package com.group5.iftt.Model.Triggers;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DayOfMonthTrigger implements Trigger, Serializable {
    private String triggerDay;

    public DayOfMonthTrigger(String dayOfMonth) {
        this.triggerDay = dayOfMonth;
    }

    @Override
    public String toString() {
        return "Day of month";
    }

    public DayOfMonthTrigger() {
    }

    public boolean isValidate() {
        try {
            int currentDayOfMonth = LocalDate.now().getDayOfMonth();
            int triggerDayInt = Integer.parseInt(triggerDay);

            return currentDayOfMonth == triggerDayInt;
        } catch (NumberFormatException | DateTimeException e) {
           System.out.println("conversione fallita");
            return false;
        }
    }

    public String getTriggerDay() {
        return triggerDay;
    }

    public void setTriggerDay(String triggerDay) {
        this.triggerDay = triggerDay;
    }
}

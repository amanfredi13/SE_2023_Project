package com.group5.iftt.Model;

import com.group5.iftt.Model.Action;
import com.group5.iftt.Model.TimeOfDayTrigger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rule {

    private StringProperty name;
    private StringProperty condition;
    private ObjectProperty<Action> action;
    private StringProperty status;
    private ObjectProperty<TimeOfDayTrigger> timeTrigger;
    private boolean actionStarted = false;
    public boolean isActionStarted(){
        return actionStarted;
    }

    public void setActionStarted(boolean actionStarted){
        this.actionStarted = actionStarted;
    }

    public Rule(String name, String condition,Action action, String status, String hour, String minute) {
        this.name = new SimpleStringProperty(name);
        this.condition = new SimpleStringProperty(condition);
        this.action = new SimpleObjectProperty<>(action);
        this.status = new SimpleStringProperty(status);
        this.timeTrigger = new SimpleObjectProperty<>(new TimeOfDayTrigger(hour, minute));
    }

    public TimeOfDayTrigger getTimeTrigger(){
        return timeTrigger.get();
    }

    public void setTimeTrigger(TimeOfDayTrigger timeTrigger) {
        this.timeTrigger.set(timeTrigger);
    }

    public boolean isTriggered() {
        return  timeTrigger.get().isValidate();
    }


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCondition() {
        return condition.get();
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public Action getAction() {
        return action.get();
    }

    public void setAction(Action action) {
        this.action.set(action);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public ObjectProperty<Action> actionProperty() {
        return action;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public boolean isActive(){
        return "Enabled".equals(status.get());
    }

}
package com.group5.iftt.Model;
import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Rule implements Serializable{

    private transient StringProperty name;
    private transient StringProperty condition;
    private transient ObjectProperty<Action> action;
    private transient StringProperty status;
    private transient ObjectProperty<TimeOfDayTrigger> timeTrigger;
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
    public Rule(){}


    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject();

        String name = this.name.get();
        String condition = this.condition.get();
        Action action = this.action.get();
        String status = this.status.get();
        TimeOfDayTrigger time = this.timeTrigger.get();


        out.writeObject(name);
        out.writeObject(condition);
        out.writeObject(action);
        out.writeObject(status);
        out.writeObject(time);
        out.writeObject(actionStarted);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        String name = (String) in.readObject();
        String condition = (String) in.readObject();
        Action action = (Action) in.readObject();
        String status = (String) in.readObject();
        TimeOfDayTrigger time = (TimeOfDayTrigger) in.readObject();
        Boolean actionstarted = (Boolean) in.readObject();

        this.name = new SimpleStringProperty(name);
        this.condition = new SimpleStringProperty(condition);
        this.action = new SimpleObjectProperty<>(action);
        this.status = new SimpleStringProperty(status);
        this.timeTrigger = new SimpleObjectProperty<>(time);
        this.actionStarted = actionstarted;

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


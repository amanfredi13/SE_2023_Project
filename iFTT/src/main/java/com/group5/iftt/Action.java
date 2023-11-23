package com.group5.iftt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Action {

    private StringProperty name;
    private StringProperty condition;
    private StringProperty action;
    private StringProperty status;

    public Action(String name, String condition, String action, String status) {
        this.name = new SimpleStringProperty(name);
        this.condition = new SimpleStringProperty(condition);
        this.action = new SimpleStringProperty(action);
        this.status = new SimpleStringProperty(status);
    }

    // Getters and setters
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

    public String getAction() {
        return action.get();
    }

    public void setAction(String action) {
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

    public StringProperty actionProperty() {
        return action;
    }

    public StringProperty statusProperty() {
        return status;
    }
}
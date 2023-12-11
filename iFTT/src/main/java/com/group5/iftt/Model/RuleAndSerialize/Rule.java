package com.group5.iftt.Model.RuleAndSerialize;
import com.group5.iftt.Model.Actions.Action;
import com.group5.iftt.Model.Triggers.Trigger;
import javafx.beans.property.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class Rule implements Serializable {
    private transient StringProperty name;
    private transient ObjectProperty<Trigger> trigger;
    private transient ObjectProperty<Action> action;
    private transient StringProperty status;
    private boolean actionStarted = false;
    private LocalDateTime wakeUp;
    private int days;
    private int hours;
    private int minutes;

    public Rule(String name,Action action, Trigger trigger, String status){
        this.name = new SimpleStringProperty(name);
        this.trigger = new SimpleObjectProperty<>(trigger);
        this.action = new SimpleObjectProperty<>(action);
        this.status = new SimpleStringProperty(status);
    }
    public Rule() {
        this.name = new SimpleStringProperty(null);
        this.trigger = new SimpleObjectProperty<>(null);
        this.action = new SimpleObjectProperty<>(null);
        this.status = new SimpleStringProperty(null);
    }

    //creo serializzatori per serializzare correttamente gli oggetti di tipo rule
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject();
        //estraggo attributi oggetto
        String name = this.name.get();
        Action action = this.action.get();
        Trigger trigger = this.trigger.get();
        String status = this.status.get();



        //scrivo attributi oggetto
        out.writeObject(name);
        out.writeObject(action);
        out.writeObject(trigger);
        out.writeObject(status);
        out.writeObject(actionStarted);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //estraggo attributi oggetto leggendo da file binario
        String name = (String) in.readObject();
        Action action = (Action) in.readObject();
        Trigger trigger = (Trigger) in.readObject();
        String status = (String) in.readObject();
        Boolean actionstarted = (Boolean) in.readObject();


        //setto gli attributi letti nei rispettivi campi dell'oggetto
        this.name = new SimpleStringProperty(name);
        this.action = new SimpleObjectProperty<>(action);
        this.trigger= new SimpleObjectProperty<>(trigger);
        this.status = new SimpleStringProperty(status);
        this.actionStarted = actionstarted;
    }


    public LocalDateTime getWakeUp() {
        return wakeUp;
    }

    public void setWakeUp(LocalDateTime wakeUp) {
        this.wakeUp = wakeUp;
    }

    public boolean isSleeping() {
        if (isWakeUp()) {
            return false;
        }
        return true;
    }

    public void setRepeatValues(int days, int hours, int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    // Metodo utilizzando per impostare i valori di ripetizione per specificare quando una regola dev'essere attivata
    public void whenWakeUp() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime plus = today.plusDays(days).plusHours(hours).plusMinutes(minutes);
        this.setWakeUp(plus);
    }
    // Questo metodo controlla se è arrivato il momento di attivare la regola.
    public boolean isWakeUp() {
        LocalDateTime today = LocalDateTime.now();
        if (this.getWakeUp() == null) return true; // La regola è sempre sveglia se la sveglia è nulla
        int compare = today.compareTo(this.getWakeUp());
        return compare >= 0;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isTriggered() {
        return  trigger.get().isValidate();
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public Trigger getTrigger(){
        return trigger.get();
    }
    public void setTrigger(Trigger trigger) {this.trigger.set(trigger);}

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

    public boolean isActionStarted(){
        return actionStarted;
    }
    public void setActionStarted(boolean actionStarted){
        this.actionStarted = actionStarted;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public ObjectProperty<Trigger> triggerProperty() { return trigger;}
    public ObjectProperty<Action> actionProperty() {
        return action;
    }
    public StringProperty statusProperty() {
        return status;
    }

    public boolean isActive(){
        return "Enabled".equals(status.get());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Rule otherRule = (Rule) obj;
        // Confronta i campi StringProperty
        if (!Objects.equals(this.name.get(), otherRule.name.get())) return false;
        if (!Objects.equals(this.status.get(), otherRule.status.get())) return false;
        // Confronta i campi ObjectProperty
        if (!Objects.equals(this.action.get(), otherRule.action.get())) return false;
        if (!Objects.equals(this.trigger.getValue().toString(), otherRule.trigger.getValue().toString())) return false;
        return true;
    }
    @Override
    public String toString() {
        return "Rule{" +
                "name=" + name.get() +
                ", Action=" + action.get() +
                ", Trigger=" + trigger.get()+
                ", status=" + status.get() +
                '}';
    }

}


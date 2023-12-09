package com.group5.iftt.Model.RuleAndSerialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

// La classe estende la classe Rule e aggiunge alcune funzionalità specifiche
// per gestire le regole che coinvolgono lo sleeping.

public class RuleSleeping extends Rule {
    private LocalDateTime wakeUp;
    private int days;
    private int hours;
    private int minutes;


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(wakeUp);
        out.writeInt(days);
        out.writeInt(hours);
        out.writeInt(minutes);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        wakeUp = (LocalDateTime) in.readObject();
        days = in.readInt();
        hours = in.readInt();
        minutes = in.readInt();
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

}

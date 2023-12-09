package com.group5.iftt.Model.Triggers;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class SpecificDateTrigger implements Trigger, Serializable {
    private LocalDate triggerDate;

    public SpecificDateTrigger(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
        showAlert("Errore", "La data deve essere successiva o uguale a quella odierna");
        throw new IllegalArgumentException("La data deve essere successiva o uguale a quella odierna");
    }
        this.triggerDate = date;

    }

    @Override
    public String toString() {
        return "Giorno specifico";
    }

    public SpecificDateTrigger() {
    }

    @Override
    public boolean isValidate() {
        try {
            LocalDate currentDate = LocalDate.now();
            return currentDate.isEqual(triggerDate);
        } catch (DateTimeException e) {
            System.out.println("Errore nella gestione della data");
            return false;
        }
    }

    public LocalDate getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(LocalDate triggerDate) {
        this.triggerDate = triggerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificDateTrigger that = (SpecificDateTrigger) o;
        return Objects.equals(triggerDate, that.triggerDate);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }
        @Override
        public int hashCode () {
            return Objects.hash(triggerDate);
        }
    }

package com.group5.iftt.Model.TriggerClassStates;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class DayOfWeekState implements TriggerState {
    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar) {
        comboBox1.setVisible(true);
        comboBox1.setPromptText("Days");
        comboBox1.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        comboBoxMinute.setVisible(false);
        checkFileButton.setVisible(false);
        messageTextArea.setVisible(false);
        calendar.setVisible(false);
    }
}

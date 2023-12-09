package com.group5.iftt.Model.TriggerClassStates;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class SpecificDateState implements TriggerState {
    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar) {
        calendar.setVisible(true);
        checkFileButton.setVisible(false);
        messageTextArea.setVisible(false);
        comboBox1.setVisible(false);
        comboBoxMinute.setVisible(false);
    }
}

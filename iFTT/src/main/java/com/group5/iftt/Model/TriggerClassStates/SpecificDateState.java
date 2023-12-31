package com.group5.iftt.Model.TriggerClassStates;

import javafx.scene.control.*;

public class SpecificDateState implements TriggerState {
    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar, TextField triggerTextField) {
        calendar.setVisible(true);
        checkFileButton.setVisible(false);
        messageTextArea.setVisible(false);
        comboBox1.setVisible(false);
        comboBoxMinute.setVisible(false);
        triggerTextField.setVisible(false);
    }
}

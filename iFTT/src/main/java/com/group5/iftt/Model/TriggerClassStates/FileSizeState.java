package com.group5.iftt.Model.TriggerClassStates;

import javafx.scene.control.*;

public class FileSizeState implements TriggerState{
    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar, TextField triggerTextField) {
        checkFileButton.setVisible(true);
        checkFileButton.setText("Load File");
        messageTextArea.setVisible(false);
        comboBox1.setVisible(false);
        comboBoxMinute.setVisible(false);
        calendar.setVisible(false);
        triggerTextField.setVisible(true);
    }
}

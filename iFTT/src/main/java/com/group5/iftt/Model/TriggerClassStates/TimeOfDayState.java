package com.group5.iftt.Model.TriggerClassStates;

import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimeOfDayState implements TriggerState {

    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar, TextField triggerTextField) {
        comboBox1.setPromptText("Hour");
        comboBox1.setVisible(true);
        comboBoxMinute.setItems(FXCollections.observableArrayList((IntStream.rangeClosed(1, 59).mapToObj(i -> String.format("%02d", i)).collect(Collectors.toList()))));
        comboBox1.setItems(FXCollections.observableArrayList(IntStream.rangeClosed(1, 24).mapToObj(i -> String.format("%02d", i)).collect(Collectors.toList())));
        comboBoxMinute.setVisible(true);
        checkFileButton.setVisible(false);
        messageTextArea.setVisible(false);
        calendar.setVisible(false);
        triggerTextField.setVisible(false);
    }
}

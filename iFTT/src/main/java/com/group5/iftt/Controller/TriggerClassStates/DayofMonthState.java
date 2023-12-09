package com.group5.iftt.Controller.TriggerClassStates;

import com.group5.iftt.Model.TriggerState;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayofMonthState implements TriggerState {

    @Override
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar) {
        comboBox1.setPromptText("Days");
        comboBox1.setVisible(true);
        comboBox1.setItems(FXCollections.observableArrayList((IntStream.rangeClosed(1, 31).mapToObj(i -> String.format("%02d", i)).collect(Collectors.toList()))));
        checkFileButton.setVisible(false);
        messageTextArea.setVisible(false);
        comboBoxMinute.setVisible(false);
        calendar.setVisible(false);
    }
}

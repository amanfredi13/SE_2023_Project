package com.group5.iftt.Model;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class TriggerContext{
private TriggerState currentState;

public void TriggerContext(){
    currentState = null; //inizializzo lo stato corrente
}
public void setState(TriggerState state){
    this.currentState = state;
}
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar) {
        if(currentState != null){
            currentState.configureUI(comboBox1,  comboBoxMinute, checkFileButton, messageTextArea, calendar);
        }
    }
}

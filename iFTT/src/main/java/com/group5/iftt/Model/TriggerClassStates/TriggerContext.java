package com.group5.iftt.Model.TriggerClassStates;

import javafx.scene.control.*;

public class TriggerContext{
    private TriggerState currentState;

    public void TriggerContext(){
    currentState = null; //Inizializzo lo stato corrente
}

    //Setto lo stato in base all'azione che è stata riconosciuta
    public void setState(TriggerState state){
    this.currentState = state;
}


    //Configuro la UI in base alle componenti che desidero mostrare per il determinato trigger.
    public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar, TextField triggerTextField) {
        if(currentState != null){
            currentState.configureUI(comboBox1,  comboBoxMinute, checkFileButton, messageTextArea, calendar,triggerTextField);
        }
    }
}

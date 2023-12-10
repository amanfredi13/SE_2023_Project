package com.group5.iftt.Model.ActionClassStates;

import javafx.scene.control.*;

public class ActionContext {
    private ActionState currentState;

    public void ActionContext(){
        currentState = null; //Inizializzo lo stato corrente
    }
    //Setto lo stato in base all'azione che è stata riconosciuta
    public void setState(ActionState state){
        this.currentState = state;
    }
    //Configuro la UI in base alle componenti che desidero mostrare per la determinata azione
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton) {
        if(currentState != null){
            currentState.configureUI(loadFileButton, messageTextArea, textFieldWriteFile,  pathDestButton);
        }
    }
}

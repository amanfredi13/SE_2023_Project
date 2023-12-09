package com.group5.iftt.Model;

import javafx.scene.control.*;

public class ActionContext {
    private ActionState currentState;

    public void ActionContext(){
        currentState = null; //inizializzo lo stato corrente
    }
    public void setState(ActionState state){
        this.currentState = state;
    }
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton) {
        if(currentState != null){
            currentState.configureUI(loadFileButton, messageTextArea, textFieldWriteFile,  pathDestButton);
        }
    }
}

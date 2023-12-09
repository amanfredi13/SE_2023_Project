package com.group5.iftt.Controller.ActionClassStates;

import com.group5.iftt.Model.ActionState;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExecuteProgramState implements ActionState {
    @Override
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton) {
        loadFileButton.setText("Choose Script");
        loadFileButton.setVisible(true);
        textFieldWriteFile.setVisible(true);
        messageTextArea.setVisible(false);
        pathDestButton.setVisible(false);
    }
}

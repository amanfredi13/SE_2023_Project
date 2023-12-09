package com.group5.iftt.Controller.ActionClassStates;

import com.group5.iftt.Model.ActionState;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FileCopyFileMoveState implements ActionState {
    @Override
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton) {
        loadFileButton.setText("Load File");
        loadFileButton.setVisible(true);
        messageTextArea.setVisible(false);
        textFieldWriteFile.setVisible(false);
        pathDestButton.setVisible(true);
    }
}

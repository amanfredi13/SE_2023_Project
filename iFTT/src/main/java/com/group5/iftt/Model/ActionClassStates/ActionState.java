package com.group5.iftt.Model.ActionClassStates;
import javafx.scene.control.*;

public interface ActionState {
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton);
}

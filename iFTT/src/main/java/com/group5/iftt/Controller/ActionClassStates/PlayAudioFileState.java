package com.group5.iftt.Controller.ActionClassStates;

import com.group5.iftt.Model.ActionState;
import javafx.scene.control.*;

public class PlayAudioFileState implements ActionState {

    @Override
    public void configureUI(Button loadFileButton, TextArea messageTextArea, TextField textFieldWriteFile, Button pathDestButton) {
        // Se sì, rendi visibile il bottone
        loadFileButton.setText("Load Audio");
        loadFileButton.setVisible(true);
        messageTextArea.setVisible(false);
        textFieldWriteFile.setVisible(false);
        pathDestButton.setVisible(false);
    }
}

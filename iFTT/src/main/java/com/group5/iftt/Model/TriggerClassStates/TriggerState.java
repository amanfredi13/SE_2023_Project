package com.group5.iftt.Model.TriggerClassStates;

import javafx.scene.control.*;

public interface TriggerState {
public void configureUI(ComboBox<String> comboBox1, ComboBox<String> comboBoxMinute, Button checkFileButton, TextArea messageTextArea, DatePicker calendar, TextField triggerTextField);
}

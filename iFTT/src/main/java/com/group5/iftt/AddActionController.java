package com.group5.iftt;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddActionController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    private MainController mainController;
    @FXML
    private ComboBox triggerComboBox;
    @FXML
    private ComboBox actionComboBox;
    @FXML
    private ComboBox statusComboBox;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void addAction() {
        Action action = new Action(
                nameTextField.getText(),
                triggerComboBox.getValue().toString(),
                actionComboBox.getValue().toString(),
                statusComboBox.getValue().toString()
        );
        mainController.addAction(action);
        cancel();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        triggerComboBox.setItems(FXCollections.observableArrayList("Ora del giorno"));
        actionComboBox.setItems(FXCollections.observableArrayList("Riproduzione audio", "Avviso popup"));
        statusComboBox.setItems(FXCollections.observableArrayList("Enabled", "Disabled"));
    }
}
package com.group5.iftt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private ComboBox comboBoxOra;
    @FXML
    private ComboBox comboBoxMinute;

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
        //inizializzazione comboBox che permettono di customizzare la regola in base alle diverse operazioni possibili
        triggerComboBox.setItems(FXCollections.observableArrayList("Ora del giorno"));
        actionComboBox.setItems(FXCollections.observableArrayList("Riproduzione audio", "Avviso popup"));
        statusComboBox.setItems(FXCollections.observableArrayList("Enabled", "Disabled"));
        //inizializzazione comboBox relativi a ora e minuti.
        comboBoxOra.setItems(FXCollections.observableArrayList("01","02","03","04","05","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"));
        comboBoxMinute.setItems(FXCollections.observableArrayList("01","02","03","04","05","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"));
        comboBoxOra.setVisible(false);
        comboBoxMinute.setVisible(false);
        triggerComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Verifica se il nuovo valore contiene la stringa "Ora del giorno"
                if (newValue != null && newValue.contains("Ora del giorno")) {
                    // Se sì, rendi visibile il bottone
                    comboBoxOra.setVisible(true);
                    comboBoxMinute.setVisible(true);
                } else {
                    // Altrimenti, rendi invisibile il bottone
                    comboBoxOra.setVisible(false);
                    comboBoxMinute.setVisible(false);
                }
            }
        });

    }

}
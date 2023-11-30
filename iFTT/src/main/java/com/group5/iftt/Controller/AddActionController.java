package com.group5.iftt.Controller;

import com.group5.iftt.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
public class AddActionController implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addButton;
    private MainController mainController;
    @FXML
    private ComboBox triggerComboBox;
    @FXML
    private ComboBox<Action> actionComboBox;
    @FXML
    private ComboBox statusComboBox;
    @FXML
    private ComboBox comboBoxOra;
    @FXML
    private ComboBox comboBoxMinute;
    @FXML
    private Button loadFileButton;
    @FXML
    private Label fileNameLabel;
    private File selectedFile;
    @FXML
    private TextArea messageTextArea;
    private String filePath;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textFieldWriteFile;


    public void setFileNameLabel(Label fileNameLabel) {
        this.fileNameLabel = fileNameLabel;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void addAction() {

        Action selectedAction = actionComboBox.getValue();
        if(selectedAction == null){
            showAlert("Errore", "Seleziona un'azione");
            return;
        }
        //estraggo valori relativi alla sveglia delle due combBox
        String selectedHour = comboBoxOra.getValue().toString();
        String selectedMinute = comboBoxMinute.getValue().toString();

        Rule rule = new Rule(
                nameTextField.getText(),
                triggerComboBox.getValue().toString(),
                selectedAction,
                statusComboBox.getValue().toString(),
                selectedHour,
                selectedMinute
        );

        // Aggiungere messaggio dall'utente
        if ("Avviso popup".equals(selectedAction.toString())) {
            String userMessage = messageTextArea.getText();
            if (userMessage != null && !userMessage.isEmpty()) {
                ShowDialogBoxAction showDialogBoxAction = new ShowDialogBoxAction();
                showDialogBoxAction.setMessage(userMessage);
                rule.setAction(showDialogBoxAction);
            } else {
                showAlert("Messaggio d'avviso", "Inserisci un messaggio per l'avviso popup.");
                return;
            }
        } else {
            messageTextArea.setVisible(false);
        }

        if ("Riproduzione Audio".equals(selectedAction.toString())) {
            String filePath = selectedFile.getAbsolutePath();
            if (filePath != null && !filePath.isEmpty()) {
                PlayAudioFileAction playAudioFileAction = new PlayAudioFileAction();
                playAudioFileAction.setFilePath(filePath);
                rule.setAction(playAudioFileAction);
            } else {
                showAlert("Errore", "Problema con il caricamento del file audio");
                return;
            }
        }
        if("Scrittura EOF".equals(selectedAction.toString())){
            String writeString = textFieldWriteFile.getText();
            String filePath = selectedFile.getAbsolutePath();
            if (filePath != null && !filePath.isEmpty()) {
                WriteEofAction editFileAction = new WriteEofAction(filePath,writeString);
                editFileAction.setFilePath(filePath);
                rule.setAction(editFileAction);
            } else {
                showAlert("Errore", "Problema con il caricamento del file da sovrascrivere");
                return;
            }
        }

        mainController.addAction(rule);
        ObservableList<Rule> ruleInstance = RuleService.getInstance();
        SerializeList ser = new SerializeList(ruleInstance, "/Users/alessandromanfredi/Desktop/SE_IFTTT/SE_2023_Project/iFTT/src/main/java/com/group5/iftt/AudioPerTest/rules.txt");
        ser.serialize();
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
        actionComboBox.setItems(FXCollections.observableArrayList(new PlayAudioFileAction(), new ShowDialogBoxAction(), new WriteEofAction()));
        statusComboBox.setItems(FXCollections.observableArrayList("Enabled", "Disabled"));
        comboBoxOra.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05","06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"));
        comboBoxMinute.setItems(FXCollections.observableArrayList("00","01", "02", "03", "04", "05","06","07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"));
        comboBoxOra.setVisible(false);
        comboBoxMinute.setVisible(false);
        loadFileButton.setVisible(false);
        textFieldWriteFile.setVisible(false);

        //Le diverse comboBox effettueranno il displacement dei rispettibi bottoni o comboBox per permettere la customizzazione dell'azione
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

        actionComboBox.valueProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observable, Action oldValue, Action newValue) {
                // Verifica se il nuovo valore contiene la stringa "Ora del giorno"
                if (newValue instanceof PlayAudioFileAction) {
                    // Se sì, rendi visibile il bottone
                    loadFileButton.setText("Load Audio");
                    loadFileButton.setVisible(true);
                    messageTextArea.setVisible(false);
                    textFieldWriteFile.setVisible(false);
                } else if (newValue instanceof ShowDialogBoxAction) {
                    loadFileButton.setVisible(false);
                    messageTextArea.setVisible(true);
                    textFieldWriteFile.setVisible(false);
                } else if (newValue instanceof WriteEofAction){
                    loadFileButton.setText("Load File");
                    loadFileButton.setVisible(true);
                    textFieldWriteFile.setVisible(true);
                    messageTextArea.setVisible(false);
                }else {
                    // Altrimenti, rendi invisibile il bottone
                    loadFileButton.setVisible(false);
                    textFieldWriteFile.setVisible(false);
                }
            }
        });

    }


    @FXML
    public void addFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Scegli un file audio");
        //Cambio il filtro a seconda dell'azione che eseguo
        if("Riproduzione Audio".equals(actionComboBox.getValue())) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Audio", "*.mp3", "*.wav"));
        }
        if("Scrittura EOF".equals(actionComboBox.getValue())) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di testo", "*.txt"));
        }
        // Mostra la finestra di dialogo per la selezione del file
            selectedFile = fileChooser.showOpenDialog(new Stage());

        // Chiamare un metodo per aggiornare la Label con il nome del file selezionato
            updateFileNameLabel();
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
            }

    }

    // Metodo per aggiornare la Label con il nome del file selezionato
    private void updateFileNameLabel() {
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();

            fileNameLabel.setText(selectedFile.getName());
        } else {
            filePath = null;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}

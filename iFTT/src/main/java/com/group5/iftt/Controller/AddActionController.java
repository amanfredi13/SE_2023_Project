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
import javafx.stage.DirectoryChooser;
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
    private ComboBox<Trigger> triggerComboBox;
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
    @FXML
    private Button checkFileButton;
    @FXML
    private Button pathDestButton;

    private String selectedPath;
    private File SelectedFile;
    @FXML
    private Label pathDestLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //inizializzazione comboBox che permettono di customizzare la regola in base alle diverse operazioni possibili
        triggerComboBox.setItems(FXCollections.observableArrayList(new TimeOfDayTrigger(), new FileStateTrigger() ));
        actionComboBox.setItems(FXCollections.observableArrayList(new PlayAudioFileAction(), new ShowDialogBoxAction(), new WriteEofAction(), new ExecuteProgramAction(), new FileCopyAction(), new FileMoveAction(), new FileDeleteAction()));
        statusComboBox.setItems(FXCollections.observableArrayList("Enabled", "Disabled"));
        comboBoxOra.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"));
        comboBoxMinute.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"));
        comboBoxOra.setVisible(false);
        comboBoxMinute.setVisible(false);
        checkFileButton.setVisible(false);
        loadFileButton.setVisible(false);
        textFieldWriteFile.setVisible(false);
        messageTextArea.setVisible(false);
        pathDestButton.setVisible(false);

        //Le diverse comboBox effettueranno il displacement dei rispettibi bottoni o comboBox per permettere la customizzazione dell'azione
        triggerComboBox.valueProperty().addListener(new ChangeListener<Trigger>() {
            @Override
            public void changed(ObservableValue<? extends Trigger> observable, Trigger oldValue, Trigger newValue){
                if (newValue instanceof TimeOfDayTrigger) {
                    comboBoxOra.setVisible(true);
                    comboBoxMinute.setVisible(true);
                    checkFileButton.setVisible(false);
                    messageTextArea.setVisible(false);
                }if (newValue instanceof FileStateTrigger) {
                    checkFileButton.setVisible(true);
                    checkFileButton.setText("Load Directory");
                    messageTextArea.setVisible(true);
                    comboBoxOra.setVisible(false);
                    comboBoxMinute.setVisible(false);
                } else {
                    // Altrimenti, rendi invisibile il resto




                }

            }
        });

        actionComboBox.valueProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observable, Action oldValue, Action newValue) {
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
                } else if (newValue instanceof WriteEofAction) {
                    loadFileButton.setText("Load File");
                    loadFileButton.setVisible(true);
                    textFieldWriteFile.setVisible(true);
                    messageTextArea.setVisible(false);
                } else if (newValue instanceof ExecuteProgramAction) {
                    loadFileButton.setText("Choose Script");
                    loadFileButton.setVisible(true);
                    textFieldWriteFile.setVisible(true);
                    messageTextArea.setVisible(false);
                } else if (newValue instanceof FileCopyAction || newValue instanceof FileMoveAction) {
                    loadFileButton.setText("Load File");
                    loadFileButton.setVisible(true);
                    messageTextArea.setVisible(false);
                    textFieldWriteFile.setVisible(false);
                    pathDestButton.setVisible(true);
                }else if( newValue instanceof  FileDeleteAction){
                    loadFileButton.setText("Load File");
                    loadFileButton.setVisible(true);
                    messageTextArea.setVisible(false);
                    textFieldWriteFile.setVisible(false);
                    pathDestButton.setVisible(false);
                } else{
                    // Altrimenti, rendi invisibile il bottone
                    loadFileButton.setVisible(false);
                    textFieldWriteFile.setVisible(false);
                    pathDestButton.setVisible(false);
                }
            }
        });

    }

    @FXML
    private void addAction() {
        Rule rule = new Rule(); //inizializzo rule vuota che verrà composta estraendo i dati ai vari campi

        Action selectedAction = actionComboBox.getValue();
        if (selectedAction == null) {
            showAlert("Errore", "Seleziona un'azione");
            return;
        }
        Trigger selectedTrigger =  triggerComboBox.getValue();
        if (selectedTrigger == null) {
            showAlert("Errore", "Seleziona un trigger");
            return;
        }
        String status =  statusComboBox.getValue().toString();
        if (status == null) {
            showAlert("Errore", "Seleziona un trigger");
            return;
        }else{
            rule.setStatus(status);
        }


        //verificare di aver inseirto un nome dopo
        rule.setName(nameTextField.getText());

        //COSTRUZIONE TRIGGER
        if ("Ora del giorno".equals(selectedTrigger.toString())) {
            String selectedHour = comboBoxOra.getValue().toString();
            String selectedMinute = comboBoxMinute.getValue().toString();
            TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(selectedHour, selectedMinute);
            rule.setTrigger(timeOfDayTrigger);
        }

        //COSTRUZIONE ACTION
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
            if (!filePath.isEmpty()) {
                PlayAudioFileAction playAudioFileAction = new PlayAudioFileAction(filePath);
                rule.setAction(playAudioFileAction);
            } else {
                showAlert("Errore: ", "Problema con il caricamento del file audio.");
                return;
            }
        }
        if ("Scrittura EOF".equals(selectedAction.toString())) {
            String writeString = textFieldWriteFile.getText();
            String filePath = selectedFile.getAbsolutePath();
            if (filePath != null && !filePath.isEmpty()) {
                WriteEofAction editFileAction = new WriteEofAction(filePath, writeString);
                rule.setAction(editFileAction);
            } else {
                showAlert("Errore: ", "Problema con il caricamento del file da sovrascrivere");
                return;
            }
        }
        if ("Esegui Script".equals(selectedAction.toString())) {
            String parameters = textFieldWriteFile.getText();
            String filePath = selectedFile.getAbsolutePath();
            if (filePath != null && !filePath.isEmpty()) {
                ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(filePath, parameters);
                rule.setAction(executeProgramAction);
            } else {
                showAlert("Errore: ", "Problema con il caricamento del file da sovrascrivere");
                return;
            }
        }
        if("Copia File".equals(selectedAction.toString())){
            String filePath = selectedFile.getAbsolutePath();
            String filePathDestination = selectedPath;
            if (filePathDestination != null && !filePathDestination.isEmpty()) {
                FileCopyAction fileCopyAction = new FileCopyAction(filePath, filePathDestination);
                fileCopyAction.setCopyFile(filePath, filePathDestination);
                rule.setAction(fileCopyAction);
            } else {
                showAlert("Errore: ", "Problema con il file da copiare");
                return;
            }
        }
        if("Sposta File".equals(selectedAction.toString())){
            String filePath = selectedFile.getAbsolutePath();
            String filePathDestination = selectedPath;
            if (filePathDestination != null && !filePathDestination.isEmpty()) {
                FileMoveAction fileMoveAction = new FileMoveAction(filePath, filePathDestination);
                fileMoveAction.setMoveFile(filePath, filePathDestination);
                rule.setAction(fileMoveAction);
            } else {
                showAlert("Errore: ", "Problema con il file da copiare");
                return;
            }
        }
        if("Elimina File".equals(selectedAction.toString())) {
            String filePath = selectedFile.getAbsolutePath();
            FileDeleteAction fileDeleteAction = new FileDeleteAction(filePath);
            fileDeleteAction.setFilePath(filePath);
            rule.setAction(fileDeleteAction);
        }



        System.out.println("Oggetto che ho creato: " + rule.toString());
        mainController.addRule(rule);
        ObservableList<Rule> ruleInstance = RuleService.getInstance();
        SerializeList ser = new SerializeList(ruleInstance, "/Users/alessandromanfredi/IdeaProjects/SE_2023_Project_rev/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt");
        ser.serialize();
        cancel();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        // fileChooser.setTitle("Scegli un file");
        //Cambio il filtro a seconda dell'azione che eseguo
        if ("Riproduzione Audio".equals(actionComboBox.getValue())) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Audio", "*.mp3", "*.wav"));
        }
        if ("Scrittura EOF".equals(actionComboBox.getValue())) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di testo", "*.txt"));
        }
        if ("Esegui Script".equals(actionComboBox.getValue())) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Script Path", "*.sh"));
        }
        // Mostra la finestra di dialogo per la selezione del file
        selectedFile = fileChooser.showOpenDialog(new Stage());

        // Chiamare un metodo per aggiornare la Label con il nome del file selezionato
        updateFileNameLabel();
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }
    }

    @FXML
    public void addDirectoryTrigger(ActionEvent actionEvent) {
        if ("Esistenza file".equals(triggerComboBox.getValue())) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleziona una cartella");

            File selectedDirectory = directoryChooser.showDialog(new Stage());
            if (selectedDirectory != null) {
                filePath = selectedDirectory.getAbsolutePath();
            }
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

    @FXML
    private void openDirectoryChooser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleziona una cartella di destinazione");

        // Mostra la finestra di dialogo della cartella di destinazione
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        // Aggiorna la label
        if (selectedDirectory != null) {
            selectedPath = selectedDirectory.getAbsolutePath();
            pathDestLabel.setText(selectedPath);
        }
    }
}


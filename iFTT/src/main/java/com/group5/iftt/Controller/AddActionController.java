package com.group5.iftt.Controller;
import com.group5.iftt.Model.Actions.*;
import com.group5.iftt.Model.RuleAndSerialize.Rule;
import com.group5.iftt.Model.RuleAndSerialize.RuleService;
import com.group5.iftt.Model.RuleAndSerialize.SerializeList;
import com.group5.iftt.Model.TriggerClassStates.*;
import com.group5.iftt.Model.ActionClassStates.*;
import com.group5.iftt.Model.Triggers.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private ComboBox<String> statusComboBox;
    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private ComboBox<String> comboBoxMinute;
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
    @FXML
    private DatePicker calendar;

    private static final String BINARIES_FILEPATH = "iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt";
    private String selectedPath;
    private File SelectedFile;
    @FXML
    private Label pathDestLabel;
    @FXML
    private CheckBox repeatCheckBox;
    @FXML
    private VBox repeatVBox;
    @FXML
    private Spinner<Integer> repeatDaySpinner;
    @FXML
    private Spinner<Integer> repeatHoursSpinner;
    @FXML
    private Spinner<Integer> repeatMinutesSpinner;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //inizializzazione comboBox che permettono di customizzare la regola in base alle diverse operazioni possibili
        triggerComboBox.setItems(FXCollections.observableArrayList(new TimeOfDayTrigger(), new FileStateTrigger(), new DayOfWeekTrigger(), new DayOfMonthTrigger(), new SpecificDateTrigger()));
        actionComboBox.setItems(FXCollections.observableArrayList(new PlayAudioFileAction(), new ShowDialogBoxAction(), new WriteEofAction(), new ExecuteProgramAction(), new FileCopyAction(), new FileMoveAction(), new FileDeleteAction()));
        statusComboBox.setItems(FXCollections.observableArrayList("Enabled", "Disabled"));
        comboBoxMinute.setItems(FXCollections.observableArrayList((IntStream.rangeClosed(1, 59).mapToObj(i -> String.format("%02d", i)).collect(Collectors.toList()))));

        // Inizializzazione degli Spinner
        SpinnerValueFactory<Integer> daysFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365);
        SpinnerValueFactory<Integer> hoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        // Applico i factory agli Spinner
        repeatDaySpinner.setValueFactory(daysFactory);
        repeatHoursSpinner.setValueFactory(hoursFactory);
        repeatMinutesSpinner.setValueFactory(minutesFactory);

        comboBox1.setVisible(false);
        comboBoxMinute.setVisible(false);
        checkFileButton.setVisible(false);
        loadFileButton.setVisible(false);
        textFieldWriteFile.setVisible(false);
        messageTextArea.setVisible(false);
        pathDestButton.setVisible(false);
        calendar.setVisible(false);

        repeatVBox.setVisible(false);
        // Binding tra la visibilità dalla VBox e il valore della Checkbox
        repeatVBox.visibleProperty().bind(repeatCheckBox.selectedProperty());

        //Le diverse comboBox effettueranno il displacement dei rispettibi bottoni o comboBox per permettere la customizzazione dell'azione
        TriggerContext triggerContext = new TriggerContext(); //Creo istanza contesto Trigger applicando pattern State
        triggerComboBox.valueProperty().addListener(new ChangeListener<Trigger>() {
            @Override
            public void changed(ObservableValue<? extends Trigger> observable, Trigger oldValue, Trigger newValue){
                if (newValue instanceof TimeOfDayTrigger) {
                   triggerContext.setState(new TimeOfDayState());
                    triggerContext.configureUI(comboBox1, comboBoxMinute, checkFileButton, messageTextArea, calendar);
                }if (newValue instanceof FileStateTrigger) {
                   triggerContext.setState(new FileStateState());
                   triggerContext.configureUI(comboBox1, comboBoxMinute, checkFileButton, messageTextArea, calendar);
                } else if (newValue instanceof DayOfWeekTrigger) {
                   triggerContext.setState(new DayOfWeekState());
                   triggerContext.configureUI(comboBox1, comboBoxMinute, checkFileButton, messageTextArea, calendar);
                }else if (newValue instanceof  DayOfMonthTrigger){
                    triggerContext.setState(new TimeOfDayState());
                    triggerContext.configureUI(comboBox1, comboBoxMinute, checkFileButton, messageTextArea, calendar);
                }else if( newValue instanceof SpecificDateTrigger ){
                    triggerContext.setState(new SpecificDateState());
                    triggerContext.configureUI(comboBox1, comboBoxMinute, checkFileButton, messageTextArea, calendar);
                }

            }
        });

        ActionContext actionContext = new ActionContext(); //Creo istanza contesto Action applicando pattern State
        actionComboBox.valueProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observable, Action oldValue, Action newValue) {
                if (newValue instanceof PlayAudioFileAction) {
                    actionContext.setState(new PlayAudioFileState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                } else if (newValue instanceof ShowDialogBoxAction) {
                    actionContext.setState(new ShowDialogBoxState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                } else if (newValue instanceof WriteEofAction) {
                    actionContext.setState(new WriteEOFState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                } else if (newValue instanceof ExecuteProgramAction) {
                    actionContext.setState(new ExecuteProgramState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                } else if (newValue instanceof FileCopyAction || newValue instanceof FileMoveAction) {
                    actionContext.setState(new FileCopyFileMoveState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                }else if( newValue instanceof  FileDeleteAction){
                    actionContext.setState(new FileDeleteState());
                    actionContext.configureUI(loadFileButton, messageTextArea,  textFieldWriteFile, pathDestButton);
                }
            }
        });

    }

    @FXML
    private void addAction() {
        Rule rule = new Rule(); //inizializzo rule vuota che verrà composta estraendo i dati ai vari campi


        if (repeatCheckBox.isSelected()) {
            // Ottengo giorni, ore e minuti dagli Spinner
            int days = repeatDaySpinner.getValue();
            int hours = repeatHoursSpinner.getValue();
            int minutes = repeatMinutesSpinner.getValue();

            rule.setRepeatValues(days, hours, minutes);
            rule.whenWakeUp();
        }


        String ruleName = nameTextField.getText();
        if (ruleName.isEmpty()) {
            showAlert("Error", "Please, select e name for the Rule.");
            return;
        }
        Action selectedAction = actionComboBox.getValue();
        if (selectedAction == null) {
            showAlert("Error", "Please, select an Action for the Rule.");
            return;
        }
        Trigger selectedTrigger =  triggerComboBox.getValue();
        if (selectedTrigger == null) {
            showAlert("Error", "Please, select a Trigger for the Action");
            return;
        }
        String status =  statusComboBox.getValue().toString();
        if (status == null) {
            showAlert("Error", "Please, select a Status for the Action");
            return;
        }else{
            rule.setStatus(status);
        }


        //verificare di aver inserito un nome dopo
        rule.setName(nameTextField.getText());

        //COSTRUZIONE TRIGGER
        if ("Ora del giorno".equals(selectedTrigger.toString())) {
            String selectedHour = comboBox1.getValue().toString();
            String selectedMinute = comboBoxMinute.getValue().toString();
            TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(selectedHour, selectedMinute);
            rule.setTrigger(timeOfDayTrigger);
        }

        if ("Giorno della settimana".equals(selectedTrigger.toString())){
            String selectedDayWeek= comboBox1.getValue().toString();
            DayOfWeekTrigger dayOfWeekTrigger= new DayOfWeekTrigger(selectedDayWeek);
            rule.setTrigger(dayOfWeekTrigger);
        }

        if("Giorno del mese".equals(selectedTrigger.toString())){
            String selectedDayMonth= comboBox1.getValue().toString();
            DayOfMonthTrigger dayOfMonthTrigger= new DayOfMonthTrigger(selectedDayMonth);
            rule.setTrigger(dayOfMonthTrigger);
        }
        if("Giorno specifico".equals(selectedTrigger.toString())){
            LocalDate localDate= calendar.getValue();
            SpecificDateTrigger specificDateTrigger= new SpecificDateTrigger(localDate);
            rule.setTrigger(specificDateTrigger);
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
        SerializeList ser = new SerializeList(ruleInstance, BINARIES_FILEPATH);
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


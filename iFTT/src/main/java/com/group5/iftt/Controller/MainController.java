package com.group5.iftt.Controller;
import com.group5.iftt.Model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class MainController {

    @FXML
    private TableView<Rule> actionTable;
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;
    @FXML
    private TableColumn<Rule, Action> actionColumn;
    @FXML
    private TableColumn<Rule, String> statusColumn;
    private static final String BINARIES_FILEPATH = "iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt";
    ObservableList<Rule> rules = RuleService.getInstance();

    public void initialize() {
        //Specifico come i dati della colonna devono essere ottenuti
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        triggerColumn.setCellValueFactory(cellData -> cellData.getValue().triggerProperty());
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Configuro la cella della colonna come stato
        configureStatusCellFactory();

        //Deserializzazione e caricamento dati nella tabella
        ObservableList<Rule> ruleList = RuleService.getInstance();
        ruleList.setAll(FXCollections.observableArrayList(SerializeList.deserialize(BINARIES_FILEPATH)));
        actionTable.setItems(rules);


    }

    @FXML
    private void addRule() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group5/iftt/AddActionView.fxml"));
            AnchorPane addActionView = loader.load();
            AddActionController addActionController = loader.getController();
            addActionController.setMainController(this);
            // Carica il file CSS e aggiungilo alla scena della finestra di dialogo
            String css = this.getClass().getResource("/com/group5/iftt/Style.css").toExternalForm();
            addActionView.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setTitle("Aggiungi Regola");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(addActionView));
            stage.showAndWait();
        } catch (IOException e) {
            System.out.print("Errore: Impossibile aggiungere Regola");
        }
    }
    @FXML
    private void cancelAction() {
        // Ottieni l'azione selezionata nella tabella
        Rule selectedRule = actionTable.getSelectionModel().getSelectedItem();
        if (selectedRule != null) {
            // Rimuovi l'azione dalla tabella e dalla lista
            actionTable.getItems().remove(selectedRule);
            rules.remove(selectedRule);
            //Sovrascrivo il file delle regole serializzate senza la regola appena eliminata.
            ObservableList<Rule> ruleInstance = RuleService.getInstance(); //Riserializzo l'Observabile list aggiornata passatomi come Singleton.
            SerializeList ser = new SerializeList(ruleInstance, BINARIES_FILEPATH);
            ser.serialize();
        }else {
            showAlert("Error", "Please, select an Action to delete.");
        }
    }
    private void handleClose() {
        //Aggiorno lo stato di tutte le regole prima di chiudere l'applicazione
        System.out.println("Finestra chiusa. Eseguo azioni di chiusura...");

    }
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Metodo per configurare la cella della colonna dello stato
    private void configureStatusCellFactory() {
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

    statusColumn.setCellFactory(tc -> {
        TableCell<Rule, String> cell = createStatusCell();

        cell.setOnMouseClicked(event -> {
            if (!cell.isEmpty()) {
               Rule rule = cell.getTableRow().getItem();
               if(rule != null){
                   String currentStatus = rule.getStatus();
                   String newStatus = (currentStatus.equals("Enabled") ? "Disabled" : "Enabled");
                   if (isValidStatus(newStatus)) {
                       rule.setStatus(newStatus);
                       cell.setText(newStatus);

                       handleRuleStatusChange(rule);

                   }
               }
            }
        });

        return cell;
    });

    statusColumn.setEditable(true);
}

    // Metodo per creare la cella della colonna
    private TableCell<Rule, String> createStatusCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        };
    }

    // Metodo per gestire il click sulla cella dello stato
    @FXML
    public void handleStatusCellClick(TableColumn.CellEditEvent<Rule, String> event) {
        Rule rule = event.getRowValue();
        String currentStatus = rule.getStatus();
        String newStatus = (currentStatus.equals("Enabled") ? "Disabled" : "Enabled");

        if (isValidStatus(newStatus)) {
            rule.setStatus(newStatus);
            actionTable.refresh(); //Aggiorno la visualizzazione della tabella

        } else {
            // Annullo le modifiche se lo stato non è valido
            event.getTableView().getItems().set(event.getTablePosition().getRow(), rule);
        }
    }

    private boolean isValidStatus(String status) {
        return "Enabled".equals(status) || "Disabled".equals(status);
    }

    private void handleRuleStatusChange(Rule rule) {
        // Aggiorno la lista e salvo automaticamente nel file
        ObservableList<Rule> ruleInstance = RuleService.getInstance();
        SerializeList ser = new SerializeList(ruleInstance, BINARIES_FILEPATH);
        ser.serialize();
    }
}
package com.group5.iftt.Controller;
import com.group5.iftt.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    ObservableList<Rule> rules = RuleService.getInstance();

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        triggerColumn.setCellValueFactory(cellData -> cellData.getValue().triggerProperty());
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Deserializzazione e caricamento dati nella tabella
        ObservableList<Rule> ruleList = RuleService.getInstance();
        ruleList.setAll(FXCollections.observableArrayList(SerializeList.deserialize("/Users/alessandromanfredi/IdeaProjects/SE_2023_Project_rev/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt")));
        actionTable.setItems(rules);
    }
    @FXML
    private void addRule() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group5/iftt/AddActionView.fxml"));
            AnchorPane addActionView = loader.load();
            AddActionController addActionController = loader.getController();
            addActionController.setMainController(this);
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
        }
        //Sovrascrivo il file delle regole serializzate senza la regola appena eliminata.
        ObservableList<Rule> ruleInstance = RuleService.getInstance(); //Riserializzo l'Observabile list aggiornata passatomi come Singleton.
        SerializeList ser = new SerializeList(ruleInstance, "/Users/alessandromanfredi/IdeaProjects/SE_2023_Project_rev/iFTT/src/main/java/com/group5/iftt/componenti_prog/binaries.txt");
        ser.serialize();
    }
    private void handleClose() {
        System.out.println("Finestra chiusa. Eseguo azioni di chiusura...");

    }
    public void addRule(Rule rule) {
        rules.add(rule);
    }

}
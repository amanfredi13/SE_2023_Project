package com.group5.iftt.Controller;

import com.group5.iftt.Controller.AddActionController;
import com.group5.iftt.Model.Action;
import com.group5.iftt.Model.Rule;
import com.group5.iftt.Model.RuleService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.IOException;

public class MainController {

    @FXML
    private TableView<Rule> actionTable;

    @FXML
    private TableColumn<Rule, String> nameColumn;

    @FXML
    private TableColumn<Rule, String> conditionColumn;

    @FXML
    private TableColumn<Rule, Action> actionColumn;

    @FXML
    private TableColumn<Rule, String> statusColumn;

    @FXML
    private Button addActionButton;

    @FXML
    private Button ButtonCancel;


    ObservableList<Rule> rules = RuleService.getInstance();

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        conditionColumn.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty()); // specifico come i dati della colonna devono essere ottenuti
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Configuro la cella della colonna come stato
        configureStatusCellFactory();

        actionTable.setItems(rules);

    }


    @FXML
    private void addAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group5/iftt/AddActionView.fxml"));
            AnchorPane addActionView = loader.load();
            AddActionController addActionController = loader.getController();
            addActionController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Aggiungi azione");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(addActionView));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
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
    }

    public void addAction(Rule rule) {
        rules.add(rule);
    }

    // Metodo per configurare la cella della colonna dello stato
    private void configureStatusCellFactory(){
        statusColumn.setCellFactory(tc-> { // specifico come le celle della colonna dovrebbero essere visualizzare o personalizate
            TableCell<Rule, String> cellData = createCellFactory();
            // Gestisco il click sulla cella per cambiare stato
            cellData.setOnMouseClicked(e -> handleStatusCellClick(cellData));

            return cellData;

        });
    }

    // Metodo per creare la cella della colonna
    private TableCell<Rule, String> createCellFactory() {
        return new TableCell<Rule,String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        };
    }

    // Metodo per gestire il click sulla cella dello stato
    private void handleStatusCellClick(TableCell<Rule, String> cellData ) {
        if (!cellData.isEmpty()) {
            Rule rule = cellData.getTableRow().getItem();
            String currentStatus = rule.getStatus();
            String newStatus = (currentStatus.equals(("Enabled")) ? "Disabled" : "Enabled");

            rule.setStatus(newStatus);
            cellData.setText(newStatus);
        }
    }

}
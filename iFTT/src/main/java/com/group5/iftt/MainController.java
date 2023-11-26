package com.group5.iftt;

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
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        actionTable.setItems(rules);
    }

    @FXML
    private void addAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddActionView.fxml"));
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
    public void cancelAction(Rule rule){rules.remove(rule);}

    public void addAction(Rule rule) {
        rules.add(rule);
    }

}
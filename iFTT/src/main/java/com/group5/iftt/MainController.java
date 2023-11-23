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
import java.util.Optional;

public class MainController {

    @FXML
    private TableView<Action> actionTable;

    @FXML
    private TableColumn<Action, String> nameColumn;

    @FXML
    private TableColumn<Action, String> conditionColumn;

    @FXML
    private TableColumn<Action, String> actionColumn;

    @FXML
    private TableColumn<Action, String> statusColumn;

    @FXML
    private Button addActionButton;

    private ObservableList<Action> actions = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        conditionColumn.setCellValueFactory(cellData -> cellData.getValue().conditionProperty());
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        actionTable.setItems(actions);
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

    public void addAction(Action action) {

        actions.add(action);
    }

}
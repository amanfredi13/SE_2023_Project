package com.group5.iftt.Model.Actions;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.Serializable;

public class ShowDialogBoxAction implements Action, Serializable {
    private String message;
    private volatile boolean dialogShown = false;

    public ShowDialogBoxAction(){}

    public void setMessage(String message){
        this.message = message;
    }

    public void startAction() {
        if (!dialogShown) {
                Platform.runLater(()->{
                messageAction(message);
                dialogShown = true;
            });
        }
    }

    public void messageAction(String message){
        //mostro la finestra di dialogo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avviso");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait().ifPresent(response -> {
            dialogShown = false;
        });
    }

    public String toString() {
        return "Show popup";
    }

    public String getMessage() {
    return message;
    }
}
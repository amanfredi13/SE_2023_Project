package com.group5.iftt;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ShowDialogBoxAction implements Action {
    private String message;
    private volatile boolean dialogShown = false;

    public ShowDialogBoxAction(){}

    public void setMessage(String message){
        this.message = message;
    }

    public void startAction() {
        if (!dialogShown) {
                Platform.runLater(()->{
                    System.out.println("StartAction message");
                System.out.println(message);
                messageAction(message);
                dialogShown = true;
            });
        }
    }

    public void messageAction(String message){
        //mostrare la finestra di dialogo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Messaggio");
        //alert.setHeaderText(null);
        System.out.println(message);
        alert.setContentText(message);

        alert.showAndWait().ifPresent(response -> {
            dialogShown = false;
        });
    }

    public String toString() {
        return "Avviso popup";
    }
}